package proiect_fic.smarttrafficmanagementsystem.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContorSenzorTest {
    @Test
    @DisplayName("Test pentru schimbareStare")
    public void testSchimbaStare() {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 0);

        //action
        senzor1.schimbaStare(true);

        //assertion
        assertEquals(true, senzor1.getEnabled());


    }

    @Test
    @DisplayName("Test pentru setNumarMasini")
    public void testSetNumarMasini() {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 0);

        //action
        senzor1.setNumarMasini(10);

        //assertion
        assertEquals(10, senzor1.getNumarMasini());
    }

    @Test
    @DisplayName("Test pentru getNumarMasini")
    public void testGetNumarMasini() {
        //setup
        ContorSenzor senzor = new ContorSenzor(true, 0);

        //assertion
        assertEquals(0, senzor.getNumarMasini());
    }

    @Test
    @DisplayName("Test pentru getEnable")
    public void testGetEnable() {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 5);

        //assertion
        assertEquals(false, senzor1.getEnabled());
    }

    @Test
    @DisplayName("Test pentru  incrementNumarMasini")
    public void testIncrementNumarMasini() {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 5);

        //action
        ///aici se poate vedea cazul in care enable este fals iar numarul de masini nu se incrementeaza
        senzor1.incrementNumarMasini();

        //assertion
        assertEquals(5, senzor1.getNumarMasini());

        //action
        //aici se poate vedea cazul in care enable este true iar numarul de masini se incrementeaza
        senzor1.schimbaStare(true);
        senzor1.incrementNumarMasini();

        //assertion
        assertEquals(6, senzor1.getNumarMasini());
    }

    @Test
    @DisplayName("Test pentru  reseteazaContor")
    public void testReseteazaContor() {
        //setup
        ContorSenzor senzor1 = new ContorSenzor(false, 5);

        //action
        assertEquals(5, senzor1.getNumarMasini()); //testez numarul de masini inainte sa fiu sigura ca e diferit de 0 si ca va deveni 0 doar in momentul in care dau reset
        senzor1.reseteazaContor();

        //assertion
        assertEquals(0, senzor1.getNumarMasini());
    }
}
