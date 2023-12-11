package grupa51;

public class Semafor {
    private ContorSenzor[] contori=new ContorSenzor[3];
    private FlagSenzor[] flaguri=new FlagSenzor[3];
    private EmergencySenzor emergency;
    private String culoare;

    //constructor
    public Semafor(ContorSenzor[] contori, FlagSenzor[] flaguri, EmergencySenzor emergency, String culoare)
    {
        this.contori=contori;
        this.flaguri=flaguri;
        this.emergency=emergency;
        this.culoare=culoare;
    }

    //aflu statusul lui emergency: true sau false
    public boolean checkEmergency()
    {
        return this.emergency.getStatus();
    }

    //setter
    public void setCuloareSemafor(String culoare)
    {
        this.culoare=culoare;
    }

    //getter
    public String getCuloareSemafor()
    {
        return this.culoare;
    }
}
