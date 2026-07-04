package br.com.g4.orcamentos.service;

import br.com.g4.orcamentos.domain.Cliente;
import br.com.g4.orcamentos.domain.Cortina;
import br.com.g4.orcamentos.domain.Orcamento;
import br.com.g4.orcamentos.domain.Produto;
import br.com.g4.orcamentos.domain.StatusOrcamento;
import br.com.g4.orcamentos.domain.Toldo;
import br.com.g4.orcamentos.domain.Vendedor;
import br.com.g4.orcamentos.repository.ClienteDAO;
import br.com.g4.orcamentos.repository.OrcamentoDAO;
import br.com.g4.orcamentos.repository.ProdutoDAO;
import br.com.g4.orcamentos.repository.VendedorDAO;

import java.util.List;

public class SistemaService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private VendedorDAO vendedorDAO = new VendedorDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private OrcamentoDAO orcamentoDAO = new OrcamentoDAO();

    public Cliente cadastrarCliente(String nome, String documento, String telefone, String email, String endereco) {
        Cliente cliente = new Cliente(nome, documento, telefone, email, endereco);
        if (clienteDAO.existeDocumento(cliente.getDocumento())) {
            throw new IllegalArgumentException("CPF/CNPJ ja cadastrado.");
        }
        return clienteDAO.salvar(cliente);
    }

    public Vendedor cadastrarVendedor(String nome, String telefone, String email, double percentualComissao) {
        return vendedorDAO.salvar(new Vendedor(nome, telefone, email, percentualComissao));
    }

    public Produto cadastrarToldo(double largura, double altura, String material, String tipo, String cor) {
        return produtoDAO.salvar(new Toldo(largura, altura, material, tipo, cor));
    }

    public Produto cadastrarCortina(double largura, double altura, String tecido) {
        return produtoDAO.salvar(new Cortina(largura, altura, tecido));
    }

    public Orcamento criarOrcamento(Cliente cliente, Vendedor vendedor, List<Produto> produtos) {
        Orcamento orcamento = vendedor.criarOrcamento(cliente);
        for (Produto produto : produtos) {
            orcamento.addProduto(produto);
        }
        return orcamentoDAO.salvar(orcamento);
    }

    public void atualizarStatus(Orcamento orcamento, StatusOrcamento status) {
        orcamento.alterarStatus(status);
        orcamentoDAO.atualizarStatus(orcamento.getId(), status);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public List<Vendedor> listarVendedores() {
        return vendedorDAO.listar();
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listar();
    }

    public List<Orcamento> listarOrcamentos() {
        return orcamentoDAO.listar();
    }

    public void excluirCliente(Cliente cliente) {
        if (cliente != null && cliente.getId() != null) {
            clienteDAO.excluir(cliente.getId());
        }
    }

    public void excluirVendedor(Vendedor vendedor) {
        if (vendedor != null && vendedor.getId() != null) {
            vendedorDAO.excluir(vendedor.getId());
        }
    }

    public void excluirProduto(Produto produto) {
        if (produto != null && produto.getId() != null) {
            produtoDAO.excluir(produto.getId());
        }
    }

    public void excluirOrcamento(Orcamento orcamento) {
        if (orcamento != null && orcamento.getId() != null) {
            orcamentoDAO.excluir(orcamento.getId());
        }
    }
}
