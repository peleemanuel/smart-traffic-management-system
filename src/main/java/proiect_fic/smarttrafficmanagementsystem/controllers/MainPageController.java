package proiect_fic.smarttrafficmanagementsystem.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import proiect_fic.smarttrafficmanagementsystem.models.Memorie;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends MainPageControllerVariables implements Initializable {

    private int value;
    private int[] debite = new int[4];
    private float[] coefs = new float[4];
    private DebitSpinner[] debitSpinners = new DebitSpinner[4];
    private CheckboxesSemafor[] checkboxesSemafors = new CheckboxesSemafor[4];
    private FazaSemafor fazaSemafor;
    private boolean coldStart;
    private int semaforEmergency = -1;

    private final SemaforImages[] semafoareImages = new SemaforImages[4];
    private int index;

    private final Label[] semafoareLabels = new Label[4];
    private final Label[] Col_debite = new Label[4];
    private final Label[] Col_coefs = new Label[4];
    private final CheckBox[] emergencies = new CheckBox[4];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        semafoareImages[0] = new SemaforImages(rosu1, galben1, verde1);
        semafoareImages[1] = new SemaforImages(rosu2, galben2, verde2);
        semafoareImages[2] = new SemaforImages(rosu3, galben3, verde3);
        semafoareImages[3] = new SemaforImages(rosu4, galben4, verde4);

        Col_debite[0] = Col_debite_1_value;
        Col_debite[1] = Col_debite_2_value;
        Col_debite[2] = Col_debite_3_value;
        Col_debite[3] = Col_debite_4_value;

        Col_coefs[0] = Col_coef_1_value;
        Col_coefs[1] = Col_coef_2_value;
        Col_coefs[2] = Col_coef_3_value;
        Col_coefs[3] = Col_coef_4_value;

        semafoareLabels[0] = semafor1;
        semafoareLabels[1] = semafor2;
        semafoareLabels[2] = semafor3;
        semafoareLabels[3] = semafor4;

        emergencies[0] = emergency1;
        emergencies[1] = emergency2;
        emergencies[2] = emergency3;
        emergencies[3] = emergency4;

        valueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory21 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory22 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory33 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory23 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory31 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory32 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory41 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory42 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        valueFactory43 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);

        counter11.setValueFactory(valueFactory11);
        counter12.setValueFactory(valueFactory12);
        counter13.setValueFactory(valueFactory13);
        counter21.setValueFactory(valueFactory21);
        counter22.setValueFactory(valueFactory22);
        counter23.setValueFactory(valueFactory23);
        counter31.setValueFactory(valueFactory31);
        counter32.setValueFactory(valueFactory32);
        counter33.setValueFactory(valueFactory33);
        counter41.setValueFactory(valueFactory41);
        counter42.setValueFactory(valueFactory42);
        counter43.setValueFactory(valueFactory43);

        debitSpinners[0] = new DebitSpinner(counter11, counter12, counter13);
        debitSpinners[1] = new DebitSpinner(counter21, counter22, counter23);
        debitSpinners[2] = new DebitSpinner(counter31, counter32, counter33);
        debitSpinners[3] = new DebitSpinner(counter41, counter42, counter43);

        checkboxesSemafors[0] = new CheckboxesSemafor(emergency_flag11, emergency_flag12, emergency_flag13);
        checkboxesSemafors[1] = new CheckboxesSemafor(emergency_flag21, emergency_flag22, emergency_flag23);
        checkboxesSemafors[2] = new CheckboxesSemafor(emergency_flag31, emergency_flag32, emergency_flag33);
        checkboxesSemafors[3] = new CheckboxesSemafor(emergency_flag41, emergency_flag42, emergency_flag43);

        //test_button.setOnAction(e -> {
        //    value = counter43.getValue();
        //    test_label.setText(String.valueOf(value));
        //});

        init();
    }

    @FXML
    public void error() {
        semafoareImages[0].setGalben();
        semafoareImages[1].setGalben();
        semafoareImages[2].setGalben();
        semafoareImages[3].setGalben();
        unHighlight(index);
        debitSpinners[index].setEnabled(false);
        emergencies[0].setDisable(true);
        emergencies[1].setDisable(true);
        emergencies[2].setDisable(true);
        emergencies[3].setDisable(true);

        fazaSemafor = FazaSemafor.ERROR;
        actualizareStareCurenta();

        next_state_button.setDisable(true);
        error_button.setDisable(true);
    }

    @FXML
    public void init() {
        // Aici initializez valorile unde consider ca este necesar.

        /* Variabile locale */
        fazaSemafor = FazaSemafor.VERDE;
        index = 0;
        semaforEmergency = -1;
        debite[0] = 0;
        debite[1] = 0;
        debite[2] = 0;
        debite[3] = 0;

        coefs[0] = 0.0F;
        coefs[1] = 0.0F;
        coefs[2] = 0.0F;
        coefs[3] = 0.0F;

        /* FXML */
        // Initial semafoarele sunt rosii (pun pe invizibil semafoarele galben si verde)
        this.galben1.setVisible(false);
        this.verde1.setVisible(false);
        this.galben2.setVisible(false);
        this.verde2.setVisible(false);
        this.galben3.setVisible(false);
        this.verde3.setVisible(false);
        this.galben4.setVisible(false);
        this.verde4.setVisible(false);
        next_state_button.setDisable(false);

        this.index_semafor_value.setText(String.valueOf(index + 1));
        this.Tverde_value.setText("30");

        this.Col_coef_1_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_2_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_3_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_4_value.setText(String.valueOf(Memorie.getKINIT()));

        this.emergency1.setSelected(false);
        this.emergency2.setSelected(false);
        this.emergency3.setSelected(false);
        this.emergency4.setSelected(false);
        this.Emergency_value.setText("0");

        Tverde_display.setText("Tverde:");

        error_button.setDisable(false);
        /* Apelari de metode pentru initializare */
        highlight(0);
        unHighlight(1);
        unHighlight(2);
        unHighlight(3);
        semafoareImages[0].setVerde();
        semafoareImages[1].setRosu();
        semafoareImages[2].setRosu();
        semafoareImages[3].setRosu();
        updateColdStart(true);

        resetCounter(0);
        resetCounter(1);
        resetCounter(2);
        resetCounter(3);

        setDebitCol(0, 0);
        setDebitCol(1, 0);
        setDebitCol(2, 0);
        setDebitCol(3, 0);

        setCoefCol(0, 1);
        setCoefCol(1, 1);
        setCoefCol(2, 1);
        setCoefCol(3, 1);

        setCounterEnable(0, true);
        setCounterEnable(1, false);
        setCounterEnable(2, false);
        setCounterEnable(3, false);

        actualizareStareCurenta();

        enableAllEmergencyFlags();

        checkboxesSemafors[0].setDeselected();
        checkboxesSemafors[1].setDeselected();
        checkboxesSemafors[2].setDeselected();
        checkboxesSemafors[3].setDeselected();
    }

    private void highlight(int index) {
        semafoareLabels[index].setFont(Font.font(52.0));
        semafoareLabels[index].setTextFill(Color.YELLOW);
    }

    private void unHighlight(int index) {
        semafoareLabels[index].setFont(Font.font(32.0));
        semafoareLabels[index].setTextFill(Color.WHITE);
    }

    @FXML
    public void nextState() throws InterruptedException {
        switch (fazaSemafor) {
            case VERDE:
                //daca suntem pe verde, trec pe galben

                // eu sunt pe index, nu am motiv sa trec la urmatorul index
                // raman cu highlight unde sunt

                semafoareImages[index].setGalben();
                fazaSemafor = FazaSemafor.GALBEN;
                debitSpinners[index].setEnabled(false);
                actualizareStareCurenta();
                break;
            case GALBEN:
                // cand sunt pe galben, fac calculele si fac cumva sa reprezint ca in spate se fac calcule
                // cand termin calculele trec in faza de rosu

                // adaugam un delay pentru fiecare calcul de formula si afisare in panoul de informatii
                int timp;
                if (coldStart) {
                    timp = Memorie.getTMED();
                } else {
                    // trebuie sa calculez factorul de importanta
                    float imp = (float) (Memorie.getKINIT() + getAllFlagsDiff(nextIndex(index)));
                    Col_coefs[nextIndex(index)].setText(String.valueOf(imp));
                    setCoefCol(nextIndex(index), imp);

                    coefs[nextIndex(index)] = imp;

                    // trebuie sa calculez cu formula
                    timp = formulaTimpVerde(index);
                }

                // schimb timpul de verde
                Tverde_value.setText(String.valueOf(timp));
                setDebitCol(index, debitSpinners[index].getDebit());

                // dupa ce calculez bine mersi, trec in faza de rosu
                fazaSemafor = FazaSemafor.ROSU;
                actualizareStareCurenta();

                semafoareImages[index].setRosu();
                break;
            case ROSU:
                // cand sunt in rosu trebuie sa verific daca trec la verde sau la galben_intermitent
                if (getDebite() == 0) {
                    // galben intermitent => toate semafoarele sunt galbene, scot highlight
                    semafoareImages[0].setGalben();
                    semafoareImages[1].setGalben();
                    semafoareImages[2].setGalben();
                    semafoareImages[3].setGalben();

                    Tverde_display.setText("Tgalben:");
                    Tverde_value.setText("120");
                    unHighlight(index);
                    fazaSemafor = FazaSemafor.GALBEN_INTERMITENT;
                    actualizareStareCurenta();
                } else {
                    // continui la verde

                    fazaSemafor = FazaSemafor.VERDE;
                    actualizareStareCurenta();
                    // trebuie sa resetez counterele
                    resetCounter(index);

                    // nu mai am highlight pe semaforul curent, pun semaforul curent pe rosu
                    unHighlight(index);
                    semafoareImages[index].setRosu();
                    setCounterEnable(index, false);
                    // trec la urmatorul index
                    incrementIndex();
                    setCounterEnable(index, true);
                    // dau highlight si pun pe verde la semaforul la care am ajuns
                    highlight(index);
                    semafoareImages[index].setVerde();
                }
                break;
            case GALBEN_INTERMITENT:
                //cand sunt in galben intermitent verific daca stau pe loc sau trec la verde
                if (getDebite() >= 10) {
                    // ies din faza de galben_intermitent
                    init();
                }
                break;
        }

    }

    private void incrementIndex() {
        if (index == 3) {
            index = 0;
            updateColdStart(false);
        } else {
            index++;
        }
        index_semafor_value.setText(String.valueOf(index + 1));
    }

    private int nextIndex(int index) {
        return (index + 1) % 4;
    }

    private void updateColdStart(boolean state) {
        coldStart = state;
        Coldstart_value.setText(String.valueOf(coldStart));
    }

    @FXML
    public void emergency(ActionEvent event) {
        CheckBox chk = (CheckBox) event.getSource();
        if (chk.isSelected() && semaforEmergency == -1) {
            // primesc un semnal de emergency si pun pe disabled restul "butoanelor" de emergency
            fazaSemafor = FazaSemafor.EMERGENCY;
            actualizareStareCurenta();
            semaforEmergency = Integer.parseInt(chk.getId());

            unHighlight(index);
            semafoareImages[index].setRosu();
            index_semafor_value.setText(chk.getId());
            Emergency_value.setText(semaforEmergency != -1 ? String.valueOf(semaforEmergency) : "0");

            // trec la semaforul cu emergency
            index = semaforEmergency - 1;
            disableOtherCounter(index);
            semafoareImages[index].setVerde();
            highlight(index);

            // dau disable la butoane
            disableOtherEmergencyFlags(index);
            next_state_button.setDisable(true);
        } else if (!chk.isSelected()) {
            // am deselectat urgenta, deci revin la normal
            fazaSemafor = FazaSemafor.GALBEN;
            actualizareStareCurenta();
            semaforEmergency = -1;

            Emergency_value.setText(semaforEmergency != -1 ? String.valueOf(semaforEmergency) : "0");

            enableAllEmergencyFlags();
            next_state_button.setDisable(false);

            semafoareImages[index].setGalben();
            //! CE FACEM CU COUNTERELE???????????????????????????????????????????????????????????????????????????????
        }
    }

    //asta se gaseste in Semafor
    private void disableOtherCounter(int index) {
        setCounterEnable(index, true);
        setCounterEnable(nextIndex(index), false);
        setCounterEnable(nextIndex(nextIndex(index)), false);
        setCounterEnable(nextIndex(nextIndex(nextIndex(index))), false);
    }

    private void disableOtherEmergencyFlags(int index) {
        for (int i = 0; i < 4; i++) {
            if (index != i) {
                emergencies[i].setDisable(true);
            }
        }
    }

    private void enableAllEmergencyFlags() {
        for (int i = 0; i < 4; i++) {
            emergencies[i].setDisable(false);
        }
    }

    private int getDebite() {
        int aux = 0;
        for (int i = 0; i < 4; i++) {
            aux += debitSpinners[i].getDebit();
        }
        System.out.println(aux);
        return aux;
    }


    private void resetCounter(int index) {
        switch (index) {
            case 0:
                valueFactory11.setValue(0);
                valueFactory12.setValue(0);
                valueFactory13.setValue(0);
                break;

            case 1:
                valueFactory21.setValue(0);
                valueFactory22.setValue(0);
                valueFactory23.setValue(0);
                break;
            case 2:
                valueFactory31.setValue(0);
                valueFactory32.setValue(0);
                valueFactory33.setValue(0);
                break;
            case 3:
                valueFactory41.setValue(0);
                valueFactory42.setValue(0);
                valueFactory43.setValue(0);
                break;
        }
    }

    private int formulaTimpVerde(int index) {
        int rez=(int) (10 + coefs[nextIndex(index)] * (debite[nextIndex(index)] / getDebite()) * 30);
        if(rez>Memorie.getTMAX())
            return Memorie.getTMAX();
        return rez;
    }

    private void setDebitCol(int index, int value) {
        Col_debite[index].setText(String.valueOf(value));
        debite[index] = value;
    }


    private void setCoefCol(int index, float value) {
        Col_coefs[index].setText(String.valueOf(value));
    }

    //in ComputeSistem
    private float getFlagDiff(int who, int what) {
        return (float) 0.1F * (checkboxesSemafors[who].getFlags() - checkboxesSemafors[what].getFlags());
    }

    //nu mai ai nevoie ca e deja in calculCoeficient() in ComputeSistem
    private float getAllFlagsDiff(int index) {
        System.out.println(index);
        System.out.println(nextIndex(index));
        System.out.println(nextIndex(nextIndex(index)));
        System.out.println(nextIndex(nextIndex(nextIndex(index))));
        return getFlagDiff(index, nextIndex(index)) +
                getFlagDiff(index, nextIndex(nextIndex(index))) +
                getFlagDiff(index, nextIndex(nextIndex(nextIndex(index))));
    }

    private void actualizareStareCurenta() {
        stare_curenta_value.setText(String.valueOf(fazaSemafor));
    }

    private void setCounterEnable(int index, boolean bool) {
        debitSpinners[index].setEnabled(bool);
    }


}