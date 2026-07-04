package br.com.g4.orcamentos.domain;

public class Cliente implements Validavel {
    private Integer id;
    private String nome;
    private String documento;
    private String telefone;
    private String email;
    private String endereco;

    public Cliente(String nome, String documento, String telefone, String email, String endereco) {
        this.nome = normalizarTexto(nome);
        this.documento = limparDocumento(documento);
        this.telefone = telefone == null ? "" : telefone.trim();
        this.email = email == null ? "" : email.trim();
        this.endereco = endereco == null ? "" : endereco.trim();
        if (!validar()) {
            throw new IllegalArgumentException("Cliente invalido: informe nome e CPF/CNPJ com 11 ou 14 digitos.");
        }
    }

    public Cliente(Integer id, String nome, String documento, String telefone, String email, String endereco) {
        this(nome, documento, telefone, email, endereco);
        this.id = id;
    }

    public boolean validar() {
        return nome != null && !nome.trim().isEmpty() && (documento.length() == 11 || documento.length() == 14);
    }

    public static String limparDocumento(String documento) {
        return documento == null ? "" : documento.replaceAll("[^0-9]", "");
    }

    private String normalizarTexto(String valor) {
        return valor == null ? "" : valor.trim();
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

    public void setNome(String nome) {
        this.nome = normalizarTexto(nome);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = limparDocumento(documento);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone == null ? "" : telefone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco == null ? "" : endereco.trim();
    }

    public String toString() {
        return nome + " (" + documento + ")";
    }
}
