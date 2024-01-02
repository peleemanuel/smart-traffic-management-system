package proiect_fic.smarttrafficmanagementsystem.models;

//aceasta clasa are rolul de a simula senzorul cu contor ce aduna cate masini trec pe sub semafor daca sistemul este activ (enable=true)
public class ContorSenzor {
    private boolean enabled;
    private int numarMasiniDetectate;

    //constructor
    public ContorSenzor(boolean enabled, int numarMasiniDetectate) {
        this.enabled = enabled;
        this.numarMasiniDetectate = numarMasiniDetectate;
    }

    //setter
    public void schimbaStare(boolean stare) {
        this.enabled = stare;
    }

    //setter
    public void setNumarMasini(int nrMasini) {
        this.numarMasiniDetectate = Math.max(nrMasini, 0);
    }

    //getter
    public int getNumarMasini() {
        return this.numarMasiniDetectate;
    }

    //getter
    public boolean getEnabled() {
        return this.enabled;
    }

    //daca enable=false numarul de masini raname acelasi
    public void incrementNumarMasini() {
        if (this.enabled) {
            this.numarMasiniDetectate++;
        }
    }

    public void reseteazaContor() {
        this.numarMasiniDetectate = 0;
    }

}
