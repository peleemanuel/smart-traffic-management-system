package proiect_fic.smarttrafficmanagementsystem.models;

/**
 * Clasa EmergencySenzor servește ca un detector pentru situații de urgență în cadrul unui sistem de control al semafoarelor.
 * Această clasă este utilizată pentru a modifica comportamentul normal al semafoarelor,
 * permițând vehiculelor de urgență, precum ambulanțele, să treacă prin intersecții rapid și în siguranță.
 * Când senzorul de urgență este activat, el poate declanșa o schimbare a secvențelor de semafor pentru a acorda prioritate vehiculului de urgență,
 * de exemplu, schimbând semaforul la verde pentru a permite ambulanței să treacă fără întârziere.
 * Alte funcționalități pot include ajustarea timpilor de semafor în funcție de trafic și alte condiții dinamic detectate.
 */
public class EmergencySenzor {
    private boolean emergency; // Starea senzorului de urgență

    /**
     * Constructor pentru clasa EmergencySenzor care inițializează starea senzorului.
     *
     * @param initialStatus Starea inițială a senzorului de urgență, falsă dacă nu este activat.
     */
    public EmergencySenzor(boolean initialStatus) {
        this.emergency = initialStatus;
    }

    /**
     * Returnează starea curentă a senzorului de urgență.
     *
     * @return boolean - Adevărat dacă senzorul este activat, fals în caz contrar.
     */
    public boolean getStatus() {
        return this.emergency;
    }

    /**
     * Setează starea senzorului de urgență.
     *
     * @param status Starea dorită pentru senzorul de urgență, adevărat pentru activat.
     */
    public void setStatus(boolean status) {
        this.emergency = status;
    }
}
