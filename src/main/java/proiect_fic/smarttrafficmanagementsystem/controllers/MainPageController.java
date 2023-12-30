package proiect_fic.smarttrafficmanagementsystem.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private DebitSpinner[] debitSpinners = new DebitSpinner[4];
    private FazaSemafor fazaSemafor;
    private boolean coldStart;
    private int semaforEmergency = -1;

    private final SemaforImages[] semafoareImages = new SemaforImages[4];
    private int index;

    private final Label[] semafoareLabels = new Label[4];
    private final CheckBox[] emergencies = new CheckBox[4];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        semafoareImages[0] = new SemaforImages(rosu1, galben1, verde1);
        semafoareImages[1] = new SemaforImages(rosu2, galben2, verde2);
        semafoareImages[2] = new SemaforImages(rosu3, galben3, verde3);
        semafoareImages[3] = new SemaforImages(rosu4, galben4, verde4);

        semafoareLabels[0] = semafor1;
        semafoareLabels[1] = semafor2;
        semafoareLabels[2] = semafor3;
        semafoareLabels[3] = semafor4;

        emergencies[0] = emergency1;
        emergencies[1] = emergency2;
        emergencies[2] = emergency3;
        emergencies[3] = emergency4;

        debitSpinners[0] = new DebitSpinner(counter11, counter12, counter13);
        debitSpinners[1] = new DebitSpinner(counter21, counter22, counter23);
        debitSpinners[2] = new DebitSpinner(counter31, counter32, counter33);
        debitSpinners[3] = new DebitSpinner(counter41, counter42, counter43);

        SpinnerValueFactory<Integer> valueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory21 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory22 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory23 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory31 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory32 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory33 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory41 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory42 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        SpinnerValueFactory<Integer> valueFactory43 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);

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

        //test_button.setOnAction(e -> {
        //    value = counter43.getValue();
        //    test_label.setText(String.valueOf(value));
        //});

        init();
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
        enableAllEmergencyFlags();

        this.index_semafor_value.setText(String.valueOf(index + 1));
        this.Tverde_value.setText("30");
        this.Col_debite_1_value.setText("0");
        this.Col_debite_2_value.setText("0");
        this.Col_debite_3_value.setText("0");
        this.Col_debite_4_value.setText("0");
        this.Col_coef_1_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_2_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_3_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_4_value.setText(String.valueOf(Memorie.getKINIT()));

        this.emergency1.setSelected(false);
        this.emergency2.setSelected(false);
        this.emergency3.setSelected(false);
        this.emergency4.setSelected(false);
        this.Emergency_value.setText("0");

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
    public void nextState() {
        // nu mai am highlight pe semaforul curent, pun semaforul curent pe rosu
        //unHighlight(index);
        //semafoareImages[index].setRosu();
        //
        //// trec la urmatorul index
        //incrementIndex();
        //
        //// dau highlight si pun pe verde la semaforul la care am ajuns
        //highlight(index);
        //semafoareImages[index].setVerde();

        switch (fazaSemafor) {
            case VERDE:
                //daca suntem pe verde, trec pe galben

                // eu sunt pe index, nu am motiv sa trec la urmatorul index
                // raman cu highlight unde sunt
                semafoareImages[index].setGalben();
                fazaSemafor = FazaSemafor.GALBEN;
                break;
            case GALBEN:
                // cand sunt pe galben, fac calculele si fac cumva sa reprezint ca in spate se fac calcule
                // cand termin calculele trec in faza de rosu

                // adaugam un delay pentru fiecare calcul de formula si afisare in panoul de informatii
                if (coldStart) {
                    Tverde_value.setText("30");
                } else {
                    // trebuie sa calculez cu formula si sa schimb timpul de verde
                }

                // dupa ce calculez bine mersi, trec in faza de rosu
                fazaSemafor = FazaSemafor.ROSU;
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

                    unHighlight(index);
                    fazaSemafor = FazaSemafor.GALBEN_INTERMITENT;
                } else {
                    fazaSemafor = FazaSemafor.VERDE;
                    //creste index??
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

    private int nextIndex() {
        return (index + 1) % 4;
    }

    private void setSemaforRosuIndex(int index) {
        semafoareImages[index].setRosu();
    }

    private void setSemaforGalbenIndex(int index) {
        semafoareImages[index].setGalben();
    }

    private void setSemaforVerdeIndex(int index) {
        semafoareImages[index].setVerde();
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

            semaforEmergency = Integer.parseInt(chk.getId());

            unHighlight(index);
            semafoareImages[index].setRosu();
            index_semafor_value.setText(chk.getId());
            Emergency_value.setText(semaforEmergency != -1 ? String.valueOf(semaforEmergency) : "0");

            // trec la semaforul cu emergency
            index = semaforEmergency - 1;
            semafoareImages[index].setVerde();
            highlight(index);

            // dau disable la butoane
            disableOtherEmergencyFlags(index);
            next_state_button.setDisable(true);
        } else if (!chk.isSelected()) {
            // am deselectat urgenta, deci revin la normal

            semaforEmergency = -1;

            Emergency_value.setText(semaforEmergency != -1 ? String.valueOf(semaforEmergency) : "0");

            enableAllEmergencyFlags();
            next_state_button.setDisable(false);

            semafoareImages[index].setRosu();
            //! CE FACEM CU COUNTERELE???????????????????????????????????????????????????????????????????????????????
        }
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


    //public void getValueSpinner() {
    //    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
    //    valueFactory.setValue(0);
    //    test_spinner.setValueFactory(valueFactory);
    //
    //    value = test_spinner.getValue();
    //    System.out.println(value);
    //    //test_label.setText(String.valueOf(value));
    //
    //    test_spinner.valueProperty().addListener(new ChangeListener<Integer>() {
    //        @Override
    //        public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
    //            value = test_spinner.getValue();
    //            test_label.setText(String.valueOf(value));
    //        }
    //    });
    //}
}
