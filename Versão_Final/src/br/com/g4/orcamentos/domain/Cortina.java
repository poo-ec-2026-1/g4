package br.com.g4.orcamentos.domain;

public class Cortina extends Produto {
    private String tecido;

    public Cortina(double largura, double altura, String tecido) {
        super(largura, altura);
        this.tecido = tecido == null ? "" : tecido.trim();
        this.precoM2 = calcularValorMetro(tecido);
    }

    public String getCategoria() {
        return "CORTINA";
    }

    public double valorMetroQ() {
        return precoM2;
    }

    private double calcularValorMetro(String valor) {
        String base = valor == null ? "" : valor.toLowerCase();
        if (base.equals("voil")) return 40;
        if (base.equals("linho")) return 70;
        if (base.equals("blackout")) return 90;
        if (base.equals("persiana")) return 110;
        return 50;
    }

    public String getTecido() {
        return tecido;
    }
}
