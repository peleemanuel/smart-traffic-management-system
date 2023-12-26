package proiect_fic.smarttrafficmanagementsystem.models;

public class ComputeSistem {
    private Semafor[] semafoare=new Semafor[4];
    private int[] debite = new int[4];
    private int[] coef = new int[4];
    private int timpVerde;
    private int timpGalben;
    private int index;
    private int sumaDebite;
    private boolean coldstart;

    // constructor
    public ComputeSistem(Semafor[] semafoare,int[] coef, int[] debite, int timpVerde, int timpGalben, int index, int sumaDebite, boolean coldstart) {
        this.semafoare=semafoare;
        this.timpVerde = timpVerde;
        this.timpGalben = timpGalben;
        this.index = index;
        this.sumaDebite = sumaDebite;
        this.coldstart = coldstart;
        this.coef=coef;
        this.debite=debite;
    }

    // begin getters and setters
    public Semafor[] getSemafoare() {
        return semafoare;
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
    public boolean getColdstartStatus() {
        return coldstart;
    }

    public void setColdstartStatus(boolean coldstart) {
        this.coldstart = coldstart;
    }
    //end getters and setters

    //initializarea sistemului
    public void initSystem(){
        timpVerde=0;
        timpGalben=10; //am pus un timp de galben random de 10 min
        index=0;
        sumaDebite=0;

        for(int i=0;i<4;i++)
        {
            debite[i]=0;
            coef[i]=0;
        }
    }

    //functia asta imi initializeaza timpul de galben intermitent
    //ma voi folosi de ea pt contextul in care sistemul numara cate masini trec in 2 min
    //daca trec mai putin de 10 raman pe galben intermitent, daca nu sistemul se reseteaza
    public void scadereRepetataTimp(){
        this.timpGalben=this.timpGalben-2;
    }
    //dupa un ciclu complet ma uit la statusul intersectiei daca am un debit total de 0 sau mai mic decat 10 raman pe galben intermitent
    //altfel sistemul se reseteaza => coldstart=true
    public boolean checkIntersectionStatus(){
        sumaDebite=0;

        while(timpGalben!=0 || coldstart==false) {
            for (int i = 0; i < 4; i++) {
                sumaDebite = sumaDebite + debite[i];
            }

            if (sumaDebite >= 0 && sumaDebite < 10) {
                scadereRepetataTimp(); //timpul de galben mai scade cu 2 min
                coldstart = false;

            }
            else {
                initSystem(); //sistemul se reinitializeaza
                coldstart = true;
            }
        }
        return coldstart;
    }

    //calculeaza suma debitelor tuturor semafoarelor diferite de semaforul cu indexul dat ca parametru
    //functia asa ma ajuta sa calculez timpVerde in functia calculTimpVerdeUrmator
    protected int sumaDebiteSemafoareCuIndexDifferit(int index)
    {
        int sum=0;
        for(int i=0;i<4;i++)
        {
            if(i!=index)
                sum=sum+debite[i];

        }
        return sum;
    }

    //aceasta functie are ca parametru index-ul semaforului pt care calculez timpul
    //Tverde(i+1) = Tmin + k*(Debit(i+1)/Sum(Debit(i+2,..)) * (Tmax-Tmin)
    //Debit(i) = SumOfCars(i) / Tverde(i)
    public int calculTimpVerdeUrmator(int index){
        int debitSemafor;
        //calcul debit pentru semafor
        debitSemafor=debite[index]/timpVerde;

        timpVerde=Memorie.getTMIN()+ coef[index]*(debitSemafor/sumaDebiteSemafoareCuIndexDifferit(index))*(Memorie.getTMAX() - Memorie.getTMIN());
        return timpVerde;
    }


   //daca semaforul e verde fiecare counter de la semafor e pus pe enable si se reseteaza counterii
    public void enableCounters(){
        for(Semafor s: semafoare)
        {
            if(s.getCuloareSemafor().toUpperCase()=="VERDE") {
                s.enableContori();
                for(ContorSenzor c: s.getContori()) {
                    c.reseteazaContor();
                }
            }
        }
    }

    //daca semaforul e galben atunci fiecare counter de la semafor va fi pus pe disable si valorile lor se aduna intr-un registru pentru semaforul actual
    public void statusGalben(){
        for(int i=0; i<4; i++)
        {
            debite[i]=0;
            if(semafoare[i].getCuloareSemafor().toUpperCase()=="GALBEN") {
                semafoare[i].disableContori();
                for(ContorSenzor c: semafoare[i].getContori()) {
                    debite[i]=debite[i]+c.getNumarMasini();
                }
            }
        }
    }

    public void verificareStatusEmergncy(){
        Semafor semaforUrgenta=null;
        for(Semafor s:semafoare){
           if(s.checkEmergency())
           {
               semaforUrgenta=s; //retin care sefafor semnaleaza o urgenta
               //daca avem semnal de urgenta toate semafoarele trec pe rosu
               for(Semafor s1:semafoare)
               {
                   s1.setCuloareSemafor("ROSU");
               }
               s.setCuloareSemafor("VERDE"); // primul semaforul unde avem semnal urgenta trece pe verde
               break; //ies din for
           }
        }

        //verific daca semnalul de urgenta nu mai e activ, in acest caz semaforul e pus pe galben
        if(semaforUrgenta!=null) //daca am avut semnal urgenta
        if(semaforUrgenta.checkEmergency()==false)
        {
            semaforUrgenta.setCuloareSemafor("GALBEN");
        }
    }

    //functia calculeaza pe baza a doi vectori de flaguri cate elemente difera
    //se foloseste pt a calcula coeficientul de importanta k
    protected int flagDiff(FlagSenzor[] flaguri1, FlagSenzor[] flaguri2) {
        int diffFlag=0; //variabila care retine cate flag-uri sunt diferite
        for(int i=0;i<3;i++){
            if(flaguri1[i]!=flaguri2[i]) {
                diffFlag++; //aceasta diferenta poate fi 0 1 2 sau 3
            }

        }
        return diffFlag;
    }

    //calculam coeficientul de importanta dupa formula
    //k(i) = kinit + Flag_diff(flags[i], flags[i+1]) + Flag_diff(flags[i], flags[i+2]) + Flag_diff(flags[i], flags[i+3]) (factorii de diferenta a flagurilor).
    public void calculCoeficient(){
        for(int i=0;i<4;i++)
        {
            coef[i] = Memorie.getKINIT();
            for(int j=0;j<4;j++) {
                if(j!=i)
                coef[i] = coef[i] + flagDiff(semafoare[i].getFlaguri(), semafoare[j].getFlaguri());
            }
        }
    }

}
