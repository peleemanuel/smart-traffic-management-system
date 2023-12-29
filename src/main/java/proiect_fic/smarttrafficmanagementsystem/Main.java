package proiect_fic.smarttrafficmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("fxmls/Main.fxml")));
            Image icon = new Image("file:src/main/resources/proiect_fic/smarttrafficmanagementsystem/icons/semafor_verde.png");
            stage.getIcons().add(icon);
            Scene scene = new Scene(root);
            stage.setTitle("Traffic Management");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}