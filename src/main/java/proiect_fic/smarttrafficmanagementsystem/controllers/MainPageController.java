package proiect_fic.smarttrafficmanagementsystem.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import proiect_fic.smarttrafficmanagementsystem.models.Memorie;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends MainPageControllerVariables implements Initializable {

    private void highlight(Label label) {
        label.setFont(Font.font(52.0));
        label.setTextFill(Color.PURPLE);
    }

    @FXML
    public void test() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Aici initializez valorile unde consider ca este necesar.
        // ! NU SE POATE FOLOSI PENTRU INIT

        // Initial semafoarele sunt rosii (pun pe invizibil semafoarele galben si verde)
        this.galben1.setVisible(false);
        this.verde1.setVisible(false);
        this.galben2.setVisible(false);
        this.verde2.setVisible(false);
        this.galben3.setVisible(false);
        this.verde3.setVisible(false);
        this.galben4.setVisible(false);
        this.verde4.setVisible(false);

        this.index_semafor_value.setText("1");
        this.Tverde_value.setText("30");
        this.Coldstart_value.setText("true");
        this.Col_debite_1_value.setText("0");
        this.Col_debite_2_value.setText("0");
        this.Col_debite_3_value.setText("0");
        this.Col_debite_4_value.setText("0");
        this.Col_coef_1_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_2_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_3_value.setText(String.valueOf(Memorie.getKINIT()));
        this.Col_coef_4_value.setText(String.valueOf(Memorie.getKINIT()));
    }
}
