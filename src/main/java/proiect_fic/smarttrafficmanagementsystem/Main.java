package proiect_fic.smarttrafficmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proiect_fic.smarttrafficmanagementsystem.models.Memorie;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            Parent root = FXMLLoader.load(Main.class.getResource("fxmls/hello-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxmls/hello-view.fxml"));
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