package proiect_fic.smarttrafficmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;

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

    /**********
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
    protected Label Coldstart_value;
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
    /* Sfarsit LABEL PANOU LATERAL INFORMATII*/


}