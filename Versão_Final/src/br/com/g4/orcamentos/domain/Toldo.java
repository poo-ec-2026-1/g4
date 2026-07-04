package br.com.g4.orcamentos.domain;

public class Toldo extends Produto {
    private String material;
    private String tipo;
    private String cor;

    public Toldo(double largura, double altura, String material, String tipo, String cor) {
        super(largura, altura);
        this.material = material == null ? "" : material.trim();
        this.tipo = tipo == null ? "" : tipo.trim();
        this.cor = cor == null ? "" : cor.trim();
        this.precoM2 = calcularValorMetro();
    }

    public String getCategoria() {
        return "TOLDO";
    }

    public double valorMetroQ() {
        return precoM2;
    }

    private double calcularValorMetro() {
        return valorPorMaterial(material) + valorPorTipo(tipo);
    }

    private double valorPorMaterial(String valor) {
        String base = valor == null ? "" : valor.toLowerCase();
        if (base.equals("madeira")) return 250;
        if (base.equals("aluminio")) return 180;
        if (base.equals("aco") || base.equals("aco galvanizado")) return 220;
        if (base.equals("policarbonato")) return 260;
        return 150;
    }

    private double valorPorTipo(String valor) {
        String base = valor == null ? "" : valor.toLowerCase();
        if (base.equals("retratil")) return 120;
        if (base.equals("reto")) return 50;
        if (base.equals("curvo")) return 90;
        if (base.equals("articulado")) return 140;
        return 60;
    }

    public String getMaterial() {
        return material;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCor() {
        return cor;
    }
}
