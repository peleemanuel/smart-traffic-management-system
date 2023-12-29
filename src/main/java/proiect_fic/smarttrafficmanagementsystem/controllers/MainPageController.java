package proiect_fic.smarttrafficmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;

public class MainPageController {

    /********************************
     * IMAGINI SEMAFOARE INTERSECITE*
     ********************************/
    @FXML
    private ImageView rosu1;
    @FXML
    private ImageView galben1;
    @FXML
    private ImageView verde1;
    @FXML
    private ImageView rosu2;
    @FXML
    private ImageView galben2;
    @FXML
    private ImageView verde2;
    @FXML
    private ImageView rosu3;
    @FXML
    private ImageView galben3;
    @FXML
    private ImageView verde3;
    @FXML
    private ImageView rosu4;
    @FXML
    private ImageView galben4;
    @FXML
    private ImageView verde4;
    /* Sfarsit imagini semafoare */

    /********************************
     * LABEL PENTRU FIECARE SEMAFOR *
     ********************************/
    @FXML
    private Label semafor1;
    @FXML
    private Label semafor2;
    @FXML
    private Label semafor3;
    @FXML
    private Label semafor4;
    /* Sfarsit label pentru fiecare semafor*/

    /**********************************
     * SPINNER PENTRU FIECARE SEMAFOR *
     **********************************/
    @FXML
    private Spinner<Integer> counter11;
    @FXML
    private Spinner<Integer> counter12;
    @FXML
    private Spinner<Integer> counter13;
    @FXML
    private Spinner<Integer> counter21;
    @FXML
    private Spinner<Integer> counter22;
    @FXML
    private Spinner<Integer> counter23;
    @FXML
    private Spinner<Integer> counter31;
    @FXML
    private Spinner<Integer> counter32;
    @FXML
    private Spinner<Integer> counter33;
    @FXML
    private Spinner<Integer> counter41;
    @FXML
    private Spinner<Integer> counter42;
    @FXML
    private Spinner<Integer> counter43;
    /* Sfarsit spinner pentru fiecare semafor */

    /*************************
     * LABELE SEMAFOR CURENT *
     *************************/
    @FXML
    private Label Semafor_curent;
    @FXML
    private Label index_semafor_value;
    /* Sfarsit label semafor curent */

    /**********************
     * CHECKBOX EMERGENCY *
     **********************/
    @FXML
    private CheckBox emergency1;
    @FXML
    private CheckBox emergency2;
    @FXML
    private CheckBox emergency3;
    @FXML
    private CheckBox emergency4;
    /* Sfarsit checkbox emergency */

    /**********
     * BUTOANE *
     ***********/
    @FXML
    private Button next_state_button;
    @FXML
    private Button reset_button;
    /* Sfarsit butoane */

    /***********************************
     * LABELE PANOU LATERAL INFORMATII *
     ***********************************/
    @FXML
    private Label Coldstart_value;
    @FXML
    private Label Tverde_value;
    @FXML
    private Label Col_debite_1_value;
    @FXML
    private Label Col_debite_2_value;
    @FXML
    private Label Col_debite_3_value;
    @FXML
    private Label Col_debite_4_value;
    @FXML
    private Label Col_coef_1_value;
    @FXML
    private Label Col_coef_2_value;
    @FXML
    private Label Col_coef_3_value;
    @FXML
    private Label Col_coef_4_value;
    /* Sfarsit LABEL PANOU LATERAL INFORMATII*/
}