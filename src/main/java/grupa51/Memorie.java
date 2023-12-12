package grupa51;
/**
*Aceasta clasa are rolul de a  a gestiona memoria sistemului si a contribui la logica de gestionare a traficului.
**/
public class Memorie {
    private static int TMIN = 20;
    private static int TMAX = 40;
    private static int TMED = 30;
    private static int TDIF = 20;
    private static int KINIT = 1;

    private int[] debite = new int[4];
    private int[] coef = new int[4];

    public Memorie(int[] debite, int[] coef) {
        this.debite = debite;
        this.coef = coef;
    }

    //gettere
    public static int getTMIN() {
        return TMIN;
    }

    public static int getTMAX() {
        return TMAX;
    }

    public static int getTMED() {
        return TMED;
    }

    public static int getTDIF() {
        return TDIF;
    }

    public static int getKINIT() {
        return KINIT;
    }

    public int[] getDebite() {
        return this.debite;
    }

    public int[] getCoef() {
        return this.coef;
    }

    //settere
    public void setDebite(int[] debite) {
        this.debite = debite;
    }

    public void setCoef(int[] coef) {
        this.coef = coef;
    }

}
