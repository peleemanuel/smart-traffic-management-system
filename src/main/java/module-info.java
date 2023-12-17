module proiect_fic.smarttrafficmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens proiect_fic.smarttrafficmanagementsystem to javafx.fxml;
    exports proiect_fic.smarttrafficmanagementsystem;
    exports proiect_fic.smarttrafficmanagementsystem.controllers;
    opens proiect_fic.smarttrafficmanagementsystem.controllers to javafx.fxml;
    exports proiect_fic.smarttrafficmanagementsystem.models;
    opens proiect_fic.smarttrafficmanagementsystem.models to javafx.fxml;
}