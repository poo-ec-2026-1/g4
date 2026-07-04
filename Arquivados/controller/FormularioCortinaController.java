package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class FormularioCortinaController {

    @FXML
    private ComboBox<String> cbModelo;

    @FXML
    private ComboBox<String> cbMaterial;

    @FXML
    public void initialize() {

        cbModelo.getItems().addAll(
            "Cortina Rolô",
            "Cortina Romana",
            "Cortina Blackout"
        );

        cbMaterial.getItems().addAll(
            "Tecido",
            "PVC",
            "Poliéster"
        );
    }
    
}
