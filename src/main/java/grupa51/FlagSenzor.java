package grupa51;
/**
 * Clasa FlagSenzor gestioneaza detectarea prezenței autovehiculelor la un semafor.
 * Fiecare instanta a acestei clase corespunde unui contor individual de trafic,
 * care contribuie la calculul debitului si al factorului de importanta pentru durata semaforului verde.
 *
 * Comportamentul senzorului este sincronizat cu starile semafoarelor:
 * - In starea verde, senzorii sunt resetati și activati pentru a număra vehiculele.
 * - In starea galbenă, contoarele sunt dezactivate si datele acumulate sunt folosite
 * pentru calculul timpului de verde al urmatorului semafor, conform unei formule specificate.
 * - In cazul unei stari de urgenta, senzorul contribuie la identificarea si gestionarea prioritatilor de trafic.
 *
 * Senzorul poate intra si intr-o stare de exceptie in cazul detectarii unei urgente de trafic,
 * modificand comportamentul intersectiei pentru a gestiona situatia.
 *
 * In plus, in caz de accident sau defectiune tehnica, FlagSenzor contribuie la activarea starii de avarie,
 * semnalizand cu galben intermitent pana la rezolvarea problemei.
 */

public class FlagSenzor {
    private boolean carDetected;

    public FlagSenzor(boolean carDetected) {
        this.carDetected = carDetected;
    }
    //getter

    public boolean getStatus(){
        return this.carDetected;
    }
    //setter
    public void setStatus(boolean carDetected){
        this.carDetected=carDetected;
    }


}
