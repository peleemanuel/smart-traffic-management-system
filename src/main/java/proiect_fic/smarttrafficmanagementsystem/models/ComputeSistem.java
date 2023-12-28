package proiect_fic.smarttrafficmanagementsystem.models;

public class ComputeSistem {
    private Semafor[] semafoare = new Semafor[4];
    private int[] debite = new int[4];
    private int[] coef = new int[4];
    private int timpVerde;
    private int timpGalben;
    private int index;
    private int sumaDebite;
    private boolean coldstart;

    // constructor
    public ComputeSistem(Semafor[] semafoare, int[] coef, int[] debite, int timpVerde, int timpGalben, int index, int sumaDebite, boolean coldstart) {
        this.semafoare = semafoare;
        this.timpVerde = timpVerde;
        this.timpGalben = timpGalben;
        this.index = index;
        this.sumaDebite = sumaDebite;
        this.coldstart = coldstart;
        this.coef = coef;
        this.debite = debite;
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

    public int[] getCoef() {
        return coef;
    }
    public int getCoefSemafor(int index) {
        return coef[index];
    }
    public void setCoef(int[] coef) {
        this.coef = coef;
    }

    public void setTimpGalben(int timpGalben) {
        this.timpGalben = timpGalben;
    }

    public int getSumaDebite() {
        return sumaDebite;
    }

    public int[] getDebite() {
        return debite;
    }

    //returneaza debitul semaforului cu indexul dat ca parametru
    public int getDebitSemfor(int index) {
        return debite[index];
    }

    public void setDebite(int[] debite) {
        this.debite = debite;
    }

    public void setSumaDebite(int sumaDebite) {
        this.sumaDebite = sumaDebite;
    }

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
        timpGalben = 600; //am pus un timp de galben random de 10 min, timpul e in secunde deci valoarea va fi 600
        index = 0;
        sumaDebite = 0;

        for (int i = 0; i < 4; i++) {
            debite[i] = 0;
            coef[i] = 1;
        }
    }

    //functia asta imi initializeaza timpul de galben intermitent
    //ma voi folosi de ea pt contextul in care sistemul numara cate masini trec in 2 min
    //daca trec mai putin de 10 raman pe galben intermitent, daca nu sistemul se reseteaza
    public void scadereRepetataTimp() {
        this.timpGalben = this.timpGalben - 1;
    } //scadem cate o secunda

    //dupa un ciclu complet ma uit la statusul intersectiei daca am un debit total de 0 sau mai mic decat 10 raman pe galben intermitent
    //altfel sistemul se reseteaza => coldstart=true
    public boolean checkIntersectionStatus() {
        sumaDebite = 0;

        while (timpGalben != 0 || coldstart == false) {
            for (int i = 0; i < 4; i++) {
                sumaDebite = sumaDebite + debite[i];
            }

            if (sumaDebite >= 0 && sumaDebite < 10) {
                scadereRepetataTimp(); //timpul de galben mai scade cu 2 min
                coldstart = false;

            } else {
                initSystem(); //sistemul se reinitializeaza
                coldstart = true;
            }
        }
        return coldstart;
    }


    //calculeaza suma debitelor tuturor semafoarelor
    //functia asa ma ajuta sa calculez timpVerde in functia calculTimpVerdeUrmator
    protected int sumaDebiteSemafoare() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum = sum + debite[i];

        }
        return sum;
    }

    //aceasta functie are ca parametru index-ul semaforului pt care calculez timpul
    //Tverde(i+1) = Tmin + k*(Debit(i+1)/Sum(Debit(i+2,..)) * (Tmax-Tmin)
    public int calculTimpVerdeUrmator(int index) {
        timpVerde = Memorie.getTMIN() + coef[index] * debite[index] * (Memorie.getTMAX() - Memorie.getTMIN());
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
            debite[i] = 0;
            if (semafoare[i].getCuloareSemafor().toUpperCase() == "GALBEN") {
                semafoare[i].disableContori();
                for (ContorSenzor c : semafoare[i].getContori()) {
                    debite[i] = debite[i] + c.getNumarMasini();
                }
            }
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
        int diffFlag = 0; //variabila care retine cate flag-uri sunt diferite
        for (int i = 0; i < 3; i++) {
            if (flaguri1[i] != flaguri2[i]) {
                diffFlag++; //aceasta diferenta poate fi 0 1 2 sau 3
            }

        }
        return diffFlag;
    }

    //calculam coeficientul de importanta dupa formula
    //k(i) = kinit + Flag_diff(flags[i], flags[i+1]) + Flag_diff(flags[i], flags[i+2]) + Flag_diff(flags[i], flags[i+3]) (factorii de diferenta a flagurilor).
    public void calculCoeficient(int index) {
        //initializarea coeicientului de importanta pentru semaforul cu indexul dat ca parametru
        coef[index] = Memorie.getKINIT();

        for (int i = 0; i < 4; i++) {
            if (i != index)
                coef[index] = coef[index] + flagDiff(semafoare[i].getFlaguri(), semafoare[index].getFlaguri());
        }
    }
}
