package proiect_fic.smarttrafficmanagementsystem.models;

// Aecasta clasa are rolul de a mima un semafor ce are capacitatea de control asupra senzorilor specifici 
// pentru banda pe care o coordoneaza
public class Semafor {
    private ContorSenzor[] contori = new ContorSenzor[3];
    private FlagSenzor[] flaguri = new FlagSenzor[3];
    private EmergencySenzor emergency;
    private String culoare;

    //constructor
    public Semafor(ContorSenzor[] contori, FlagSenzor[] flaguri, EmergencySenzor emergency, String culoare) {
        this.contori = contori;
        this.flaguri = flaguri;
        this.emergency = emergency;
        this.culoare = culoare;
    }

    //aflu statusul lui emergency: true sau false
    public boolean checkEmergency() {
        return this.emergency.getStatus();
    }

    public void setContori(ContorSenzor[] contori) {
        this.contori = contori;
    }

    //setter
    public void setCuloareSemafor(String culoare) {
        this.culoare = culoare;
    }

    //getter
    public String getCuloareSemafor() {
        return this.culoare;
    }

    public void setEmergency(EmergencySenzor emergency) {
        this.emergency = emergency;
    }

    //getter
    public FlagSenzor[] getFlaguri() {
        return flaguri;
    }

    //getter
    public ContorSenzor[] getContori() {
        return contori;
    }

    //punem pe true toate variabilele contori
    public void enableContori() {
        for (ContorSenzor contori : this.contori) {
            contori.schimbaStare(true);
        }
    }

    //punem pe false toate variabilele contori
    public void disableContori() {
        for (ContorSenzor contori : this.contori) {
            contori.schimbaStare(false);
        }
    }

    public int checkFlags() {
        int contor = 0;
        for (FlagSenzor flaguri : this.flaguri) {
            if (flaguri.getStatus())
                contor++;
        }
        return contor;
    }

    private void disableOtherCounter(int index) {
        contori[index].schimbaStare(true);
        for (int i = 0; i < 3; i++) {
            if (i != index)
                contori[i].schimbaStare(false);

        }
    }

}

