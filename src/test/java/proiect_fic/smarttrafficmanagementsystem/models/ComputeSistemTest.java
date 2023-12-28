package proiect_fic.smarttrafficmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComputeSistemTest {
    private ComputeSistem computeSistem;
    private int[] coef = {1, 1, 1, 1};
    private int[] debite = {0, 0, 0, 0};
    private int timpVerde = 120;
    private int timpGalben = 60;
    private int index = 0;
    private int sumaDebite = 0;
    private boolean coldstart = false;
    private Memorie mem=new Memorie(coef, debite);
    @BeforeEach
    public void setUp() {
        // inițializare array ContorSenzor
        ContorSenzor[] contori1 = new ContorSenzor[]{
                new ContorSenzor(false, 5),
                new ContorSenzor(false, 10),
                new ContorSenzor(true, 15)
        };
        ContorSenzor[] contori2 = new ContorSenzor[]{
                new ContorSenzor(true, 5),
                new ContorSenzor(true, 10),
                new ContorSenzor(true, 15)
        };

        //initializare array flaguri
        FlagSenzor[] flaguri1 = new FlagSenzor[]{
                new FlagSenzor(true),
                new FlagSenzor(false),
                new FlagSenzor(false)
        };
        FlagSenzor[] flaguri2 = new FlagSenzor[]{
                new FlagSenzor(true),
                new FlagSenzor(true),
                new FlagSenzor(true)
        };

        // inițializare EmergencySenzor
        EmergencySenzor emergencySenzor1 = new EmergencySenzor(false);
        EmergencySenzor emergencySenzor2 = new EmergencySenzor(true);
        // inițializare array Semafoare
        Semafor[] semafoare = new Semafor[]
                {
                        new Semafor(contori1, flaguri1, emergencySenzor1, "ROSU"),
                        new Semafor(contori2, flaguri1, emergencySenzor2, "ROSU"),
                        new Semafor(contori1, flaguri2, emergencySenzor1, "VERDE"),
                        new Semafor(contori2, flaguri2, emergencySenzor1, "GALBEN"),
                };

        // initializam computeSistem cu valorile de mai sus
        computeSistem = new ComputeSistem(semafoare, mem, timpVerde, timpGalben, index, sumaDebite, coldstart);
    }

    @DisplayName("Test pentru initSystem")
    @Test
    void testInitSystem() {
        computeSistem.initSystem();
        assertEquals(0, computeSistem.getTimpVerde()); //timpul de verde ar trebui resetat la 0 dupa initializare
        assertEquals(600, computeSistem.getTimpGalben()); //timpul de galben ar trebui sa fie 600 dupa initializare
        assertEquals(0, computeSistem.getSumaDebite()); //suma debite ar trebui sa fie 0 dupa initializare
    }

    //verific daca timpul de galben este micsorat cu 1
    @Test
    public void testScadereRepetataTimp() {
        int timpGalbenInitial = computeSistem.getTimpGalben();
        computeSistem.scadereRepetataTimp();
        assertEquals(timpGalbenInitial - 1, computeSistem.getTimpGalben());
    }

/*    @Test
    public void testCheckIntersectionStatus() {
        // Setează condițiile inițiale
        computeSistem.initSystem();
        // Aici ar trebui să simulezi debitele și alte condiții necesare
        boolean result = computeSistem.checkIntersectionStatus();
        // Verifică rezultatul așteptat
        assertFalse(result, "Coldstart ar trebui să fie fals dacă intersecția este sub pragul de debite");
        // Adaugă alte verificări pentru diferite scenarii
    }*/

    @Test
    public void testCalculTimpVerdeUrmator() {
        int index = 3;
        int[] coef = {1, 1, 1, 1};
        int[] debite = {10, 20, 10, 30};
        mem.setDebite(debite);
        mem.setCoef(coef);
        assertEquals(computeSistem.calculTimpVerdeUrmator(index), 620);

    }

    @Test
    public void testEnableCounters() {
        computeSistem.enableCounters(2); //semafor verde => fac enable

        for (int i = 0; i < 3; i++) //ma uit la fiecare counter sa verific daca pt verde e enabled (true)
        {
            assertEquals(computeSistem.getSemafor(2).getContori()[i].getEnabled(), true); //enabled

            //verific sa am facut reset la nr de masini pt fiecare counter de la semaforul verde
            assertEquals(computeSistem.getSemafor(2).getContori()[i].getNumarMasini(), 0);

        }

    }

    @Test
    public void testStatusGalben() {

        computeSistem.statusGalben();

        for (int i = 0; i < 3; i++) //ma uit la fiecare counter sa verific daca pt galben e disabled (false)
        {
            assertEquals(computeSistem.getSemafor(3).getContori()[i].getEnabled(), false); //semaforul 3 e galben

        }

        //verific debitul semaforului galben
        assertEquals(mem.getDebitSemfor(3), 30);
    }

    //am testat 2 functii in acest test pentru ca ele depind una de alta
    //adica functia statusGalbenDupaStatusEmergency() va fi apelata doar in cazul in care am avut o urgenta
    @Test
    public void testVerificareStatusEmergencySIstatusGalbenDupaStatusEmergency() {
        //semaforul 1 are o urgenta
        EmergencySenzor s = new EmergencySenzor(true);
        computeSistem.getSemafor(1).setEmergency(s);
        computeSistem.verificareStatusEmergency();
        //toate semafoarele inafara de semaforul 1 care semnaleaza o urgenta ar trebui sa aiba culoarea rosu
        assertEquals(computeSistem.getSemafor(0).getCuloareSemafor(), "ROSU");
        assertEquals(computeSistem.getSemafor(2).getCuloareSemafor(), "ROSU");
        assertEquals(computeSistem.getSemafor(3).getCuloareSemafor(), "ROSU");

        //semaforul 1 e singurul semafor care trebuie sa aiba culoarea verde
        assertEquals(computeSistem.getSemafor(1).getCuloareSemafor(), "VERDE");

        //punem semnalul de emergency inapoi pe false
        EmergencySenzor s1 = new EmergencySenzor(false);
        computeSistem.getSemafor(1).setEmergency(s1);

        //pt semaforul care a avut activ semnalul de emergency apelam functia statusGalbenDupaStatusEmergency()
        computeSistem.statusGalbenDupaStatusEmergency(computeSistem.getSemafor(1));
        //acum semaforul 1 trebuie sa aiba culoarea galben
        assertEquals(computeSistem.getSemafor(1).getCuloareSemafor(), "GALBEN");

    }

    @Test
    public void testCalculCoeficient()
    {
        //testam pt semaforul cu indexul 1

        computeSistem.calculCoeficient(1);

        assertEquals(mem.getCoefSemafor(1), 7);
    }
}
