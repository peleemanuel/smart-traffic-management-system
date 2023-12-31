package proiect_fic.smarttrafficmanagementsystem.models;

/**
 * Aceasta clasa are rolul de a  a gestiona memoria sistemului si a contribui la logica de gestionare a traficului.
 **/
public class Memorie {
    private static int TMIN = 20;
    private static int TMAX = 40;
    private static int TMED = 30;
    private static int TDIF = 20;
    private static int KINIT = 1;

    private int[] debite = new int[4];
    private float[] coef = new float[4];

    public Memorie(int[] debite, float[] coef) {
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

    public float[] getCoef() {
        return this.coef;
    }

    public float getCoefSemafor(int index) {
        return coef[index];
    }

    //returneaza debitul semaforului cu indexul dat ca parametru
    public int getDebitSemfor(int index) {
        return debite[index];
    }

    //settere
    public void setDebite(int[] debite) {
        this.debite = debite;
    }

    public void setDebitSemafor(int index, int d) {
        this.debite[index] = d;
    }

    public void setCoef(float[] coef) {
        this.coef = coef;
    }

    public void setCoefSemafor(int index, float c) {
        this.coef[index] = c;
    }

}
