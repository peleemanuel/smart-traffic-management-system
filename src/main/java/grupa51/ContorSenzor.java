package grupa51;
//aceasta clasa are rolul de a simula senzorul cu contor ce aduna cate masini trec pe sub semafor daca sistemul este activ (enable=true)
public class ContorSenzor {
    private boolean enable;
    private int numarMasiniDetectate;

    //contructor
   public ContorSenzor(boolean enable, int numarMasiniDetectate)
    {
        this.enable=enable;
        this.numarMasiniDetectate=numarMasiniDetectate;
    }

    //setter
    public void schimbaStare(boolean stare)
    {
        this.enable=stare;
    }

    //setter
    public void setNumarMasini(int nrMasini)
    {
        this.numarMasiniDetectate=nrMasini;
    }

    //getter
    public int getNumarMasini()
    {
        return this.numarMasiniDetectate;
    }

    //getter
    public boolean getEnable(){
        return this.enable;
    }

    //daca enable=false numarul de masini raname acelasi
    public void incrementNumarMasini(){
        if(this.enable==true)
        {
            this.numarMasiniDetectate++;
        }
    }

    public void reseteazaContor(){
        this.numarMasiniDetectate=0;
    }
}
