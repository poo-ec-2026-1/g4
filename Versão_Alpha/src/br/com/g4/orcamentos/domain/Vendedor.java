package br.com.g4.orcamentos.domain;

public class Vendedor implements Validavel {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private double percentualComissao;

    public Vendedor(String nome, String telefone, String email, double percentualComissao) {
        this.nome = nome == null ? "" : nome.trim();
        this.telefone = telefone == null ? "" : telefone.trim();
        this.email = email == null ? "" : email.trim();
        this.percentualComissao = percentualComissao;
        if (!validar()) {
            throw new IllegalArgumentException("Vendedor invalido: informe nome e percentual de comissao entre 0 e 100.");
        }
    }

    public Vendedor(Integer id, String nome, String telefone, String email, double percentualComissao) {
        this(nome, telefone, email, percentualComissao);
        this.id = id;
    }

    public boolean validar() {
        return nome != null && !nome.trim().isEmpty() && percentualComissao >= 0 && percentualComissao <= 100;
    }

    public Orcamento criarOrcamento(Cliente cliente) {
        return new Orcamento(cliente, this);
    }

    public double calcularComissao(double valorVenda) {
        if (valorVenda < 0) {
            throw new IllegalArgumentException("Valor de venda nao pode ser negativo.");
        }
        return valorVenda * percentualComissao / 100.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public String toString() {
        return nome + " (" + percentualComissao + "%)";
    }
}
