package br.com.g4.orcamentos.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orcamento {
    private Integer id;
    private Cliente cliente;
    private Vendedor vendedor;
    private List<Produto> produtos = new ArrayList<Produto>();
    private StatusOrcamento status = StatusOrcamento.EM_ANALISE;
    private LocalDateTime criadoEm = LocalDateTime.now();

    public Orcamento(Cliente cliente, Vendedor vendedor) {
        if (cliente == null || vendedor == null) {
            throw new IllegalArgumentException("Orcamento precisa de cliente e vendedor.");
        }
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public Orcamento(Integer id, Cliente cliente, Vendedor vendedor, StatusOrcamento status, LocalDateTime criadoEm) {
        this(cliente, vendedor);
        this.id = id;
        this.status = status == null ? StatusOrcamento.EM_ANALISE : status;
        this.criadoEm = criadoEm == null ? LocalDateTime.now() : criadoEm;
    }

    public void addProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto nao pode ser nulo.");
        }
        if (status != StatusOrcamento.EM_ANALISE) {
            throw new IllegalStateException("Produtos so podem ser alterados em orcamentos em analise.");
        }
        produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.calcularPreco();
        }
        return total;
    }

    public void aprovar() {
        alterarStatus(StatusOrcamento.APROVADO);
    }

    public void recusar() {
        alterarStatus(StatusOrcamento.RECUSADO);
    }

    public void cancelar() {
        alterarStatus(StatusOrcamento.CANCELADO);
    }

    public void reabrirAnalise() {
        if (status == StatusOrcamento.CANCELADO) {
            throw new IllegalStateException("Orcamento cancelado nao pode ser reaberto.");
        }
        status = StatusOrcamento.EM_ANALISE;
    }

    public void alterarStatus(StatusOrcamento novoStatus) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("Status nao pode ser nulo.");
        }
        if (status == StatusOrcamento.CANCELADO && novoStatus != StatusOrcamento.CANCELADO) {
            throw new IllegalStateException("Orcamento cancelado nao aceita nova transicao.");
        }
        status = novoStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = new ArrayList<Produto>();
        if (produtos != null) {
            this.produtos.addAll(produtos);
        }
    }

    public StatusOrcamento getStatus() {
        return status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public int getTotalProdutos() {
        return produtos.size();
    }

    public double getComissaoVendedor() {
        return vendedor.calcularComissao(calcularTotal());
    }

    public String toString() {
        return "#" + id + " - " + cliente.getNome() + " - " + status;
    }
}
