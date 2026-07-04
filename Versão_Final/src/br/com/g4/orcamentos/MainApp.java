package br.com.g4.orcamentos;

import br.com.g4.orcamentos.database.Database;
import br.com.g4.orcamentos.domain.Cliente;
import br.com.g4.orcamentos.domain.Orcamento;
import br.com.g4.orcamentos.domain.Produto;
import br.com.g4.orcamentos.domain.StatusOrcamento;
import br.com.g4.orcamentos.domain.Vendedor;
import br.com.g4.orcamentos.service.SistemaService;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {
    private SistemaService service = new SistemaService();

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private ObservableList<Vendedor> vendedores = FXCollections.observableArrayList();
    private ObservableList<Produto> produtos = FXCollections.observableArrayList();
    private ObservableList<Orcamento> orcamentos = FXCollections.observableArrayList();

    private ComboBox<Cliente> comboClienteOrcamento = new ComboBox<Cliente>();
    private ComboBox<Vendedor> comboVendedorOrcamento = new ComboBox<Vendedor>();
    private ListView<Produto> listaProdutosOrcamento = new ListView<Produto>();
    private TableView<Orcamento> tabelaOrcamentos = new TableView<Orcamento>();

    public void start(Stage stage) {
        Database.inicializar();
        carregarDados();

        TabPane abas = new TabPane();
        abas.getTabs().add(criarAbaClientes());
        abas.getTabs().add(criarAbaVendedores());
        abas.getTabs().add(criarAbaProdutos());
        abas.getTabs().add(criarAbaOrcamentos());

        BorderPane root = new BorderPane();
        Label titulo = new Label("Sistema de Gestao de Orcamentos - Toldos e Cortinas");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 16 16 8 16;");
        root.setTop(titulo);
        root.setCenter(abas);

        Scene scene = new Scene(root, 1120, 720);
        stage.setTitle("Gestao de Orcamentos");
        stage.setScene(scene);
        stage.show();
    }

    private Tab criarAbaClientes() {
        TextField nome = campo("Nome");
        TextField documento = campo("CPF ou CNPJ");
        TextField telefone = campo("Telefone");
        TextField email = campo("Email");
        TextField endereco = campo("Endereco");

        Button salvar = new Button("Cadastrar cliente");
        Button excluir = new Button("Excluir selecionado");

        TableView<Cliente> tabela = new TableView<Cliente>(clientes);
        tabela.getColumns().add(coluna("ID", "id", 70));
        tabela.getColumns().add(coluna("Nome", "nome", 220));
        tabela.getColumns().add(coluna("Documento", "documento", 150));
        tabela.getColumns().add(coluna("Telefone", "telefone", 140));
        tabela.getColumns().add(coluna("Email", "email", 220));
        tabela.getColumns().add(coluna("Endereco", "endereco", 260));

        salvar.setOnAction(e -> executar("Cliente cadastrado.", () -> {
            service.cadastrarCliente(nome.getText(), documento.getText(), telefone.getText(), email.getText(), endereco.getText());
            limpar(nome, documento, telefone, email, endereco);
            carregarDados();
        }));

        excluir.setOnAction(e -> executar("Cliente excluido.", () -> {
            service.excluirCliente(tabela.getSelectionModel().getSelectedItem());
            carregarDados();
        }));

        VBox conteudo = new VBox(12, formulario(new Label("Novo cliente"), nome, documento, telefone, email, endereco, salvar, excluir), tabela);
        VBox.setVgrow(tabela, Priority.ALWAYS);
        return aba("Clientes", conteudo);
    }

    private Tab criarAbaVendedores() {
        TextField nome = campo("Nome");
        TextField telefone = campo("Telefone");
        TextField email = campo("Email");
        TextField comissao = campo("Comissao %");

        Button salvar = new Button("Cadastrar vendedor");
        Button excluir = new Button("Excluir selecionado");

        TableView<Vendedor> tabela = new TableView<Vendedor>(vendedores);
        tabela.getColumns().add(coluna("ID", "id", 70));
        tabela.getColumns().add(coluna("Nome", "nome", 250));
        tabela.getColumns().add(coluna("Telefone", "telefone", 160));
        tabela.getColumns().add(coluna("Email", "email", 260));
        tabela.getColumns().add(coluna("Comissao", "percentualComissao", 120));

        salvar.setOnAction(e -> executar("Vendedor cadastrado.", () -> {
            service.cadastrarVendedor(nome.getText(), telefone.getText(), email.getText(), numero(comissao.getText()));
            limpar(nome, telefone, email, comissao);
            carregarDados();
        }));

        excluir.setOnAction(e -> executar("Vendedor excluido.", () -> {
            service.excluirVendedor(tabela.getSelectionModel().getSelectedItem());
            carregarDados();
        }));

        VBox conteudo = new VBox(12, formulario(new Label("Novo vendedor"), nome, telefone, email, comissao, salvar, excluir), tabela);
        VBox.setVgrow(tabela, Priority.ALWAYS);
        return aba("Vendedores", conteudo);
    }

    private Tab criarAbaProdutos() {
        ComboBox<String> categoria = new ComboBox<String>(FXCollections.observableArrayList("TOLDO", "CORTINA"));
        categoria.getSelectionModel().select("TOLDO");
        TextField largura = campo("Largura em metros");
        TextField altura = campo("Altura em metros");
        TextField material = campo("Material do toldo");
        TextField tipo = campo("Tipo do toldo");
        TextField cor = campo("Cor do toldo");
        TextField tecido = campo("Tecido da cortina");

        Button salvar = new Button("Cadastrar produto");
        Button excluir = new Button("Excluir selecionado");

        TableView<Produto> tabela = new TableView<Produto>(produtos);
        tabela.getColumns().add(coluna("ID", "id", 70));
        tabela.getColumns().add(coluna("Categoria", "categoria", 110));
        tabela.getColumns().add(coluna("Largura", "largura", 90));
        tabela.getColumns().add(coluna("Altura", "altura", 90));
        tabela.getColumns().add(coluna("Material", "material", 140));
        tabela.getColumns().add(coluna("Tipo", "tipo", 140));
        tabela.getColumns().add(coluna("Cor", "cor", 120));
        tabela.getColumns().add(coluna("Tecido", "tecido", 140));
        tabela.getColumns().add(coluna("Preco m2", "precoM2", 100));
        TableColumn<Produto, String> total = new TableColumn<Produto, String>("Total");
        total.setCellValueFactory(c -> new SimpleStringProperty(moeda(c.getValue().calcularPreco())));
        total.setPrefWidth(120);
        tabela.getColumns().add(total);

        salvar.setOnAction(e -> executar("Produto cadastrado.", () -> {
            if ("TOLDO".equals(categoria.getValue())) {
                service.cadastrarToldo(numero(largura.getText()), numero(altura.getText()), material.getText(), tipo.getText(), cor.getText());
            } else {
                service.cadastrarCortina(numero(largura.getText()), numero(altura.getText()), tecido.getText());
            }
            limpar(largura, altura, material, tipo, cor, tecido);
            carregarDados();
        }));

        excluir.setOnAction(e -> executar("Produto excluido.", () -> {
            service.excluirProduto(tabela.getSelectionModel().getSelectedItem());
            carregarDados();
        }));

        GridPane grid = grid();
        grid.add(new Label("Categoria"), 0, 0);
        grid.add(categoria, 1, 0);
        grid.add(largura, 2, 0);
        grid.add(altura, 3, 0);
        grid.add(material, 0, 1);
        grid.add(tipo, 1, 1);
        grid.add(cor, 2, 1);
        grid.add(tecido, 3, 1);
        grid.add(new HBox(8, salvar, excluir), 0, 2, 4, 1);

        VBox conteudo = new VBox(12, new Label("Novo produto"), grid, tabela);
        VBox.setVgrow(tabela, Priority.ALWAYS);
        return aba("Produtos", conteudo);
    }

    private Tab criarAbaOrcamentos() {
        comboClienteOrcamento.setItems(clientes);
        comboVendedorOrcamento.setItems(vendedores);
        listaProdutosOrcamento.setItems(produtos);
        listaProdutosOrcamento.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaProdutosOrcamento.setPrefHeight(170);

        Button criar = new Button("Criar orcamento");
        Button excluir = new Button("Excluir selecionado");
        ComboBox<StatusOrcamento> status = new ComboBox<StatusOrcamento>(FXCollections.observableArrayList(StatusOrcamento.values()));
        status.getSelectionModel().select(StatusOrcamento.APROVADO);
        Button atualizarStatus = new Button("Atualizar status");

        tabelaOrcamentos.setItems(orcamentos);
        tabelaOrcamentos.getColumns().add(coluna("ID", "id", 70));
        TableColumn<Orcamento, String> cliente = new TableColumn<Orcamento, String>("Cliente");
        cliente.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCliente().getNome()));
        cliente.setPrefWidth(220);
        TableColumn<Orcamento, String> vendedor = new TableColumn<Orcamento, String>("Vendedor");
        vendedor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getVendedor().getNome()));
        vendedor.setPrefWidth(200);
        tabelaOrcamentos.getColumns().add(cliente);
        tabelaOrcamentos.getColumns().add(vendedor);
        tabelaOrcamentos.getColumns().add(coluna("Status", "status", 130));
        tabelaOrcamentos.getColumns().add(coluna("Itens", "totalProdutos", 80));
        TableColumn<Orcamento, String> valor = new TableColumn<Orcamento, String>("Total");
        valor.setCellValueFactory(c -> new SimpleStringProperty(moeda(c.getValue().calcularTotal())));
        valor.setPrefWidth(130);
        TableColumn<Orcamento, String> comissao = new TableColumn<Orcamento, String>("Comissao");
        comissao.setCellValueFactory(c -> new SimpleStringProperty(moeda(c.getValue().getComissaoVendedor())));
        comissao.setPrefWidth(130);
        tabelaOrcamentos.getColumns().add(valor);
        tabelaOrcamentos.getColumns().add(comissao);

        criar.setOnAction(e -> executar("Orcamento criado.", () -> {
            Cliente clienteSelecionado = comboClienteOrcamento.getValue();
            Vendedor vendedorSelecionado = comboVendedorOrcamento.getValue();
            List<Produto> produtosSelecionados = new ArrayList<Produto>(listaProdutosOrcamento.getSelectionModel().getSelectedItems());
            if (clienteSelecionado == null || vendedorSelecionado == null || produtosSelecionados.isEmpty()) {
                throw new IllegalArgumentException("Selecione cliente, vendedor e pelo menos um produto.");
            }
            service.criarOrcamento(clienteSelecionado, vendedorSelecionado, produtosSelecionados);
            listaProdutosOrcamento.getSelectionModel().clearSelection();
            carregarDados();
        }));

        atualizarStatus.setOnAction(e -> executar("Status atualizado.", () -> {
            Orcamento selecionado = tabelaOrcamentos.getSelectionModel().getSelectedItem();
            if (selecionado == null) {
                throw new IllegalArgumentException("Selecione um orcamento.");
            }
            service.atualizarStatus(selecionado, status.getValue());
            carregarDados();
        }));

        excluir.setOnAction(e -> executar("Orcamento excluido.", () -> {
            service.excluirOrcamento(tabelaOrcamentos.getSelectionModel().getSelectedItem());
            carregarDados();
        }));

        GridPane grid = grid();
        grid.add(new Label("Cliente"), 0, 0);
        grid.add(comboClienteOrcamento, 1, 0);
        grid.add(new Label("Vendedor"), 2, 0);
        grid.add(comboVendedorOrcamento, 3, 0);
        grid.add(new Label("Produtos"), 0, 1);
        grid.add(listaProdutosOrcamento, 1, 1, 3, 1);
        grid.add(new HBox(8, criar, excluir, status, atualizarStatus), 1, 2, 3, 1);

        VBox conteudo = new VBox(12, new Label("Novo orcamento"), grid, tabelaOrcamentos);
        VBox.setVgrow(tabelaOrcamentos, Priority.ALWAYS);
        return aba("Orcamentos", conteudo);
    }

    private void carregarDados() {
        clientes.setAll(service.listarClientes());
        vendedores.setAll(service.listarVendedores());
        produtos.setAll(service.listarProdutos());
        orcamentos.setAll(service.listarOrcamentos());
    }

    private TextField campo(String prompt) {
        TextField campo = new TextField();
        campo.setPromptText(prompt);
        campo.setPrefWidth(180);
        return campo;
    }

    private GridPane grid() {
        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(12));
        return grid;
    }

    private VBox formulario(Label titulo, TextField a, TextField b, TextField c, TextField d, TextField e, Button salvar, Button excluir) {
        GridPane grid = grid();
        grid.add(a, 0, 0);
        grid.add(b, 1, 0);
        grid.add(c, 2, 0);
        grid.add(d, 3, 0);
        grid.add(e, 0, 1, 2, 1);
        grid.add(new HBox(8, salvar, excluir), 2, 1, 2, 1);
        return new VBox(8, titulo, grid);
    }

    private VBox formulario(Label titulo, TextField a, TextField b, TextField c, TextField d, Button salvar, Button excluir) {
        GridPane grid = grid();
        grid.add(a, 0, 0);
        grid.add(b, 1, 0);
        grid.add(c, 2, 0);
        grid.add(d, 3, 0);
        grid.add(new HBox(8, salvar, excluir), 0, 1, 4, 1);
        return new VBox(8, titulo, grid);
    }

    private <S, T> TableColumn<S, T> coluna(String titulo, String propriedade, int largura) {
        TableColumn<S, T> coluna = new TableColumn<S, T>(titulo);
        coluna.setCellValueFactory(new PropertyValueFactory<S, T>(propriedade));
        coluna.setPrefWidth(largura);
        return coluna;
    }

    private Tab aba(String titulo, VBox conteudo) {
        conteudo.setPadding(new Insets(16));
        Tab tab = new Tab(titulo, conteudo);
        tab.setClosable(false);
        return tab;
    }

    private void limpar(TextField... campos) {
        for (TextField campo : campos) {
            campo.clear();
        }
    }

    private double numero(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("Informe todos os campos numericos.");
        }
        return Double.parseDouble(texto.replace(",", "."));
    }

    private String moeda(double valor) {
        return "R$ " + String.format("%.2f", valor);
    }

    private void executar(String sucesso, Acao acao) {
        try {
            acao.executar();
            alerta(Alert.AlertType.INFORMATION, "Sucesso", sucesso);
        } catch (Exception ex) {
            alerta(Alert.AlertType.ERROR, "Atencao", ex.getMessage());
        }
    }

    private void alerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private interface Acao {
        void executar();
    }
}
