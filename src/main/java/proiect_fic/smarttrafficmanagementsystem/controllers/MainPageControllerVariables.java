package proiect_fic.smarttrafficmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

public abstract class MainPageControllerVariables {
    /********************************
     * IMAGINI SEMAFOARE INTERSECITE*
     ********************************/
    @FXML
    protected ImageView rosu1;
    @FXML
    protected ImageView galben1;
    @FXML
    protected ImageView verde1;
    @FXML
    protected ImageView rosu2;
    @FXML
    protected ImageView galben2;
    @FXML
    protected ImageView verde2;
    @FXML
    protected ImageView rosu3;
    @FXML
    protected ImageView galben3;
    @FXML
    protected ImageView verde3;
    @FXML
    protected ImageView rosu4;
    @FXML
    protected ImageView galben4;
    @FXML
    protected ImageView verde4;
    /* Sfarsit imagini semafoare */

    /********************************
     * LABEL PENTRU FIECARE SEMAFOR *
     ********************************/
    @FXML
    protected Label semafor1;
    @FXML
    protected Label semafor2;
    @FXML
    protected Label semafor3;
    @FXML
    protected Label semafor4;
    /* Sfarsit label pentru fiecare semafor*/

    /**********************************
     * SPINNER PENTRU FIECARE SEMAFOR *
     **********************************/
    @FXML
    protected Spinner<Integer> counter11;
    @FXML
    protected Spinner<Integer> counter12;
    @FXML
    protected Spinner<Integer> counter13;
    @FXML
    protected Spinner<Integer> counter21;
    @FXML
    protected Spinner<Integer> counter22;
    @FXML
    protected Spinner<Integer> counter23;
    @FXML
    protected Spinner<Integer> counter31;
    @FXML
    protected Spinner<Integer> counter32;
    @FXML
    protected Spinner<Integer> counter33;
    @FXML
    protected Spinner<Integer> counter41;
    @FXML
    protected Spinner<Integer> counter42;
    @FXML
    protected Spinner<Integer> counter43;
    /* Sfarsit spinner pentru fiecare semafor */

    /*************************
     * LABELE SEMAFOR CURENT *
     *************************/
    @FXML
    protected Label Semafor_curent;
    @FXML
    protected Label index_semafor_value;
    /* Sfarsit label semafor curent */

    /**********************
     * CHECKBOX EMERGENCY *
     **********************/
    @FXML
    protected CheckBox emergency1;
    @FXML
    protected CheckBox emergency2;
    @FXML
    protected CheckBox emergency3;
    @FXML
    protected CheckBox emergency4;
    /* Sfarsit checkbox emergency */

    /***********
     * BUTOANE *
     ***********/
    @FXML
    protected Button next_state_button;
    @FXML
    protected Button reset_button;
    /* Sfarsit butoane */

    /***********************************
     * LABELE PANOU LATERAL INFORMATII *
     ***********************************/
    @FXML
    protected Label stare_curenta_value;
    @FXML
    protected Label Coldstart_value;
    @FXML
    protected Label Tverde_display;
    @FXML
    protected Label Tverde_value;
    @FXML
    protected Label Col_debite_1_value;
    @FXML
    protected Label Col_debite_2_value;
    @FXML
    protected Label Col_debite_3_value;
    @FXML
    protected Label Col_debite_4_value;
    @FXML
    protected Label Col_coef_1_value;
    @FXML
    protected Label Col_coef_2_value;
    @FXML
    protected Label Col_coef_3_value;
    @FXML
    protected Label Col_coef_4_value;
    @FXML
    protected Label Emergency_value;
    /* Sfarsit LABEL PANOU LATERAL INFORMATII*/

    /*************************
     * SPINNER VALUE FACTORY *
     *************************/
    SpinnerValueFactory<Integer> valueFactory11;
    SpinnerValueFactory<Integer> valueFactory12;
    SpinnerValueFactory<Integer> valueFactory13;
    SpinnerValueFactory<Integer> valueFactory21;
    SpinnerValueFactory<Integer> valueFactory22;
    SpinnerValueFactory<Integer> valueFactory33;
    SpinnerValueFactory<Integer> valueFactory23;
    SpinnerValueFactory<Integer> valueFactory31;
    SpinnerValueFactory<Integer> valueFactory32;
    SpinnerValueFactory<Integer> valueFactory41;
    SpinnerValueFactory<Integer> valueFactory42;
    SpinnerValueFactory<Integer> valueFactory43;
    /* Sfarsit spinner value factory */

    /***********************
     * CHECKBOX FLAG BANDA *
     ***********************/
    @FXML
    protected CheckBox emergency_flag11;
    @FXML
    protected CheckBox emergency_flag12;
    @FXML
    protected CheckBox emergency_flag13;
    @FXML
    protected CheckBox emergency_flag21;
    @FXML
    protected CheckBox emergency_flag22;
    @FXML
    protected CheckBox emergency_flag23;
    @FXML
    protected CheckBox emergency_flag31;
    @FXML
    protected CheckBox emergency_flag32;
    @FXML
    protected CheckBox emergency_flag33;
    @FXML
    protected CheckBox emergency_flag41;
    @FXML
    protected CheckBox emergency_flag42;
    @FXML
    protected CheckBox emergency_flag43;
    /* Sfarsit checkbox flag banda */
}

class CheckboxesSemafor {
    private final CheckBox[] checkboxes = new CheckBox[3];

    public CheckboxesSemafor(CheckBox checkbox1, CheckBox checkbox2, CheckBox checkbox3) {
        checkboxes[0] = checkbox1;
        checkboxes[1] = checkbox2;
        checkboxes[2] = checkbox3;
    }

    public int getFlags() {
        int rez = 0;
        for (int i = 0; i < 3; i++)
            if (checkboxes[i].isSelected())
                rez++;
        return rez;
    }

    public void setEnabled(boolean bool) {
        for (int i = 0; i < 3; i++)
            checkboxes[i].setDisable(!bool);
    }

    public void setDeselected() {
        for (int i = 0; i < 3; i++)
            checkboxes[i].setSelected(false);
    }

}

class DebitSpinner {
    private final List<Spinner<Integer>> spinners = new ArrayList<>(3);

    public DebitSpinner(Spinner<Integer> spinner1, Spinner<Integer> spinner2, Spinner<Integer> spinner3) {
        spinners.add(spinner1);
        spinners.add(spinner2);
        spinners.add(spinner3);
    }

    public int getDebit() {
        return spinners.get(0).getValue() + spinners.get(1).getValue() + spinners.get(2).getValue();
    }
    //public void setCountersZero(){
    //    spinners.get(0).setV
    //}
}

class SemaforImages {
    private final ImageView[] culori = new ImageView[3];

    public SemaforImages(ImageView rosu, ImageView galben, ImageView verde) {
        culori[0] = rosu;
        culori[1] = galben;
        culori[2] = verde;
    }

    public void setRosu() {
        culori[0].setVisible(true);
        culori[1].setVisible(false);
        culori[2].setVisible(false);
    }

    public void setGalben() {
        culori[0].setVisible(false);
        culori[1].setVisible(true);
        culori[2].setVisible(false);
    }

    public void setVerde() {
        culori[0].setVisible(false);
        culori[1].setVisible(false);
        culori[2].setVisible(true);
    }
}