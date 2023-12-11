package grupa51;

public class ContorSenzor {
    private boolean enable;
    private int numarMasiniDetectate;

    //contructor
   public ContorSenzor(boolean enable, int numarMasiniDetectate)
    {
        enable=enable;
        numarMasiniDetectate=numarMasiniDetectate;
    }

    //setter
    public void schimbaStare(boolean stare)
    {
        enable=stare;
    }

    //setter
    public void setNumarMasini(int nrMasini)
    {
        numarMasiniDetectate=nrMasini;
    }

    //getter
    public int getNumarMasini()
    {
        return numarMasiniDetectate;
    }

    //getter
    public boolean getEnable(){
        return enable;
    }

    //daca enable=false numarul de masini raname acelasi
    public void incrementNumarMasini(){
        if(enable==true)
        {
            numarMasiniDetectate++;
        }
    }

    public void reseteazaContor(){
        numarMasiniDetectate=0;
    }
}
