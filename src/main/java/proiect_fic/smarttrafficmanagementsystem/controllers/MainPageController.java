package proiect_fic.smarttrafficmanagementsystem.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import proiect_fic.smarttrafficmanagementsystem.models.Memorie;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends MainPageControllerVariables implements Initializable {
    private boolean isEmergency;
    private boolean coldStart;
    private int semaforEmergency = -1;

    private final SemaforImages[] semafoareImages = new SemaforImages[4];
    private int index;

    private final Label[] semafoareLabels = new Label[4];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        semafoareImages[0] = new SemaforImages(rosu1, galben1, verde1);
        semafoareImages[1] = new SemaforImages(rosu2, galben2, verde2);
        semafoareImages[2] = new SemaforImages(rosu3, galben3, verde3);
        semafoareImages[3] = new SemaforImages(rosu4, galben4, verde4);
        updateColdStart(true);
        init();
    }

    @FXML
    public void init() {
        // Aici initializez valorile unde consider ca este necesar.

        /* Variabile locale */
        semafoareLabels[0] = semafor1;
        semafoareLabels[1] = semafor2;
        semafoareLabels[2] = semafor3;
        semafoareLabels[3] = semafor4;
        isEmergency = false;
        index = 0;

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

        /* Apelari de metode pentru initializare */
        unHighlight(0);
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
        unHighlight(index);
        semafoareImages[index].setRosu();
        incrementIndex();
        highlight(index);
        semafoareImages[index].setVerde();
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
            //!! TREBUIE SA ADAUG UN LABEL CA SA VAD UNDE AM EMERGENCY
        }
    }
}
