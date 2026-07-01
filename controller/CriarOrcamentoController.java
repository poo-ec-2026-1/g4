package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class CriarOrcamentoController {

    @FXML
    private ComboBox<String> cbCliente;

    @FXML
    private Spinner<Integer> spQuantidadeProdutos;

    @FXML
    private Spinner<Integer> spQtdToldos;

    @FXML
    private Spinner<Integer> spQtdCortinas;
    @FXML
    public void initialize() {

        spQuantidadeProdutos.setValueFactory(
            new javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0)
        );

        spQtdToldos.setValueFactory(
            new javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0)
        );

        spQtdCortinas.setValueFactory(
            new javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0)
        );
    }

    @FXML
    private void abrirFormularioProdutos(ActionEvent event) throws IOException {

        int qtdProdutos = spQuantidadeProdutos.getValue();
        int qtdToldos = spQtdToldos.getValue();
        int qtdCortinas = spQtdCortinas.getValue();

        
        if (qtdProdutos != qtdToldos + qtdCortinas) {
            System.out.println("Quantidade inválida!");
            return;
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/FormularioOrcamento.fxml"));

        Parent root = loader.load();

        
        FormularioOrcamentoController controller = loader.getController();

      
        controller.receberDados(qtdToldos, qtdCortinas);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.show();

    }

}
