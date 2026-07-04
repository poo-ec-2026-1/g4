package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class FormularioToldoController {

    @FXML
    private ComboBox<String> cbModelo;

    @FXML
    private ComboBox<String> cbMaterial;

    @FXML
    public void initialize() {

        cbModelo.getItems().addAll(
            "Toldo Retrátil",
            "Toldo Fixo",
            "Toldo Articulado"
        );

        cbMaterial.getItems().addAll(
            "Lona",
            "Policarbonato",
            "PVC"
        );
    }
}
