package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class FormularioOrcamentoController {

    @FXML
    private VBox containerProdutos;

    public void receberDados(int qtdToldos, int qtdCortinas) {

        try {

            for (int i = 1; i <= qtdToldos; i++) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/GUI/FormularioToldo.fxml"));

                Parent formulario = loader.load();

                containerProdutos.getChildren().add(formulario);

            }

            for (int i = 1; i <= qtdCortinas; i++) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/GUI/FormularioCortina.fxml"));

                Parent formulario = loader.load();

                containerProdutos.getChildren().add(formulario);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void finalizarCompra() {
       

            System.out.println("Finalizando compra...");

            //calcularOrcamento()
    }
}
