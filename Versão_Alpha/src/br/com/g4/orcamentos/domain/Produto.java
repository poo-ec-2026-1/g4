package br.com.g4.orcamentos.domain;

public abstract class Produto {
    private Integer id;
    protected double largura;
    protected double altura;
    protected double precoM2;

    public Produto(double largura, double altura) {
        if (largura <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Largura e altura devem ser maiores que zero.");
        }
        this.largura = largura;
        this.altura = altura;
    }

    public abstract String getCategoria();

    public abstract double valorMetroQ();

    public double getArea() {
        return largura * altura;
    }

    public double calcularPreco() {
        return getArea() * valorMetroQ();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLargura() {
        return largura;
    }

    public double getAltura() {
        return altura;
    }

    public double getPrecoM2() {
        return valorMetroQ();
    }

    public String getResumo() {
        return getCategoria() + " - " + String.format("%.2fm2", getArea()) + " - R$ " + String.format("%.2f", calcularPreco());
    }

    public String getMaterial() {
        return "";
    }

    public String getTipo() {
        return "";
    }

    public String getCor() {
        return "";
    }

    public String getTecido() {
        return "";
    }

    public String toString() {
        return getResumo();
    }
}
