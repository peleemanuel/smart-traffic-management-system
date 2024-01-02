package proiect_fic.smarttrafficmanagementsystem.models;

public class ComputeSistem {
    private Semafor[] semafoare = new Semafor[4];
    private Memorie mem; //de aici obtin debite[], coef[] si alte constante
    private int timpVerde;
    private int timpGalben;
    private int index;
    private int sumaDebite;
    private boolean coldstart;
    private EmergencySenzor[] emergencies=new EmergencySenzor[4]; //fiecare set de benzi din interesectie care un emergencysezor reprezentat ca un checkbox

    // constructor
    public ComputeSistem(Semafor[] semafoare, Memorie mem, int timpVerde, int timpGalben, int index, int sumaDebite, boolean coldstart) {
        this.semafoare = semafoare;
        this.timpVerde = timpVerde;
        this.timpGalben = timpGalben;
        this.index = index;
        this.sumaDebite = sumaDebite;
        this.coldstart = coldstart;
        this.mem = mem;
    }

    // begin getters and setters
    public Semafor[] getSemafoare() {
        return semafoare;
    }

    //returneaza semaforul cu indexul dat ca parametru
    public Semafor getSemafor(int index) {
        return semafoare[index];
    }

    public void setSemafoare(Semafor[] semafoare) {
        this.semafoare = semafoare;
    }

    public int getTimpVerde() {
        return timpVerde;
    }

    public int getTimpGalben() {
        return timpGalben;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public void setTimpGalben(int timpGalben) {
        this.timpGalben = timpGalben;
    }

    public int getSumaDebite() {
        return sumaDebite;
    }

    public void setSumaDebite(int sumaDebite) {
        this.sumaDebite = sumaDebite;
    }
//*********** se foloseste in loc de updateColdtart()
    public boolean getColdstartStatus() {
        return coldstart;
    }

    public void setColdstartStatus(boolean coldstart) {
        this.coldstart = coldstart;
    }
    //end getters and setters

    //initializarea sistemului
    public void initSystem() {
        timpVerde = 0;
        timpGalben = Memorie.getTMED();
        index = 0;
        sumaDebite = 0;
        coldstart = true;
        int[] debite = new int[4];
        float[] coef = new float[4];
        for (int i = 0; i < 4; i++) {
            debite[i] = 0;
            coef[i] = Memorie.getKINIT();
        }

        mem.setCoef(coef);
        mem.setDebite(debite);
    }


    //dupa un ciclu complet ma uit la statusul intersectiei daca am un debit total de 0 sau mai mic decat 10 raman pe galben intermitent
    //altfel sistemul se reseteaza => coldstart=true
    public boolean checkIntersectionStatus() {
        sumaDebite = 0;

        while (timpGalben != 0 || coldstart == false) {
            for (int i = 0; i < 4; i++) {
                sumaDebite = sumaDebite + mem.getDebitSemfor(i);
            }

            if (sumaDebite >= 0 && sumaDebite < 10) {
                continue;

            } else {
                initSystem(); //sistemul se reinitializeaza

            }
        }
        return coldstart;
    }

public int sumaDebite(){
        sumaDebite=0;
        for(int i=0;i<4;i++)
        {
            sumaDebite=sumaDebite+mem.getDebitSemfor(i);
        }
        return sumaDebite;
}
    //aceasta functie are ca parametru index-ul semaforului pt care calculez timpul
    //Tverde(i+1) = Tmin + k*(Debit(i+1)/Sum(Debit(i+2,..)) * (Tmax-Tmin)
    public int calculTimpVerdeUrmator(int index) {
        //if(sumaDebite!=0) //aceasta conditie e doar ca sa nu mai avem erori
        timpVerde = (int) (Memorie.getTMIN() + mem.getCoefSemafor(index) *( mem.getDebitSemfor(index)/sumaDebite()) * (Memorie.getTMAX() - Memorie.getTMIN())); //! debit(index)/ suma_debite
       if(timpVerde>Memorie.getTMAX())
           return Memorie.getTMAX();
        return timpVerde;
    }

    //daca semaforul e verde fiecare counter de la semafor e pus pe enable si se reseteaza counterii
    public void enableCounters(int index) {
        if (semafoare[index].getCuloareSemafor().toUpperCase() == "VERDE") {
            semafoare[index].enableContori();
            for (ContorSenzor c : semafoare[index].getContori()) {
                c.reseteazaContor();
            }
        }
    }

    //daca semaforul e galben atunci fiecare counter de la semafor va fi pus pe disable si valorile lor se aduna intr-un registru pentru semaforul actual
    public void statusGalben() {
        for (int i = 0; i < 4; i++) {
            int rez = 0;
            if (semafoare[i].getCuloareSemafor().toUpperCase() == "GALBEN") {
                semafoare[i].disableContori();
                for (ContorSenzor c : semafoare[i].getContori()) {
                    rez = rez + c.getNumarMasini();
                }
            }
            mem.setDebitSemafor(i, rez);
        }
    }

    public void verificareStatusEmergency() {
        int index = -1;

        for (int i = 0; i < 4; i++) {
            if (semafoare[i].checkEmergency()) {
                index = i;//retin primul semafor care semnaleaza o urgenta
                break; //ies din for
            }
        }

        //daca avem semnal de urgenta toate semafoarele trec pe rosu
        if (index != -1) {
            for (int i = 0; i < 4; i++) {
                semafoare[i].setCuloareSemafor("ROSU");
            }

            semafoare[index].setCuloareSemafor("VERDE"); // primul semaforul unde avem semnal urgenta trece pe verde
        }
    }

    //aceasta functie vine ca o completare a functiei anterioare numita verificareStatusEmergency()
    //dupa ce am avut status de emergency si a trecut ambulanta/politia/etc pot pune inapoi pe false semnalul de emergency
    //atunci pt semaforul respectiv voi apela aceasta functie
    public void statusGalbenDupaStatusEmergency(Semafor semaforUrgenta) {
        //verific daca semnalul de urgenta nu mai e activ, in acest caz semaforul e pus pe galben
        if (semaforUrgenta.checkEmergency() == false) {
            semaforUrgenta.setCuloareSemafor("GALBEN");
        }
    }

    //functia calculeaza pe baza a doi vectori de flaguri cate elemente difera
    //se foloseste pt a calcula coeficientul de importanta k
    protected int flagDiff(FlagSenzor[] flaguri1, FlagSenzor[] flaguri2) {
        int diffFlag1 = 0, diffFlag2=0; //variabila care retine cate flag-uri sunt diferite
        for (int i = 0; i < 3; i++) {
            if (flaguri1[i].getStatus()==true)
            {
                diffFlag1++; //aceasta diferenta poate fi 0 1 2 sau 3
            }
                if(flaguri2[i].getStatus()==true) {
                diffFlag2++; //aceasta diferenta poate fi 0 1 2 sau 3
            }

        }
        return diffFlag1-diffFlag2; //practic daca avem flagurile egale aceasta diferenta trebuie sa fie o
    }

    //calculam coeficientul de importanta dupa formula
    //k(i) = kinit + Flag_diff(flags[i], flags[i+1]) + Flag_diff(flags[i], flags[i+2]) + Flag_diff(flags[i], flags[i+3]) (factorii de diferenta a flagurilor).
    public void calculCoeficient(int index) {
        //initializarea coeicientului de importanta pentru semaforul cu indexul dat ca parametru
        float rez = Memorie.getKINIT();

        for (int i = 0; i < 4; i++) {
            if (i != index)
                rez = (float) (rez + flagDiff(semafoare[i].getFlaguri(), semafoare[index].getFlaguri()) * 0.1);
        }
        mem.setCoefSemafor(index, rez);
    }


    public void incrementIndex() {
        if (index == 3) {
            index = 0;
            coldstart=false;
        } else {
            index++;
        }
    }

    //calculeaza indexul urmator
    private int nextIndex(int index) {
        return (index + 1) % 4;
    }



}
