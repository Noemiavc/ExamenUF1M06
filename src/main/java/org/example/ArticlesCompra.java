package org.example;

import java.io.Serializable;

public class ArticlesCompra implements Serializable {
    private String descripcio, unitat, seccio;
    private double quantitat,preu;

    private static final long serialVersionUID = 1112358L;


    public ArticlesCompra() {}

    public ArticlesCompra(String descripcio, String unitat, String seccio, double quantitat,double preu) {
        this.descripcio = descripcio;
        this.unitat = unitat;
        this.seccio = seccio;
        this.quantitat = quantitat;
        this.preu = preu;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getUnitat() {
        return unitat;
    }

    public void setUnitat(String unitat) {
        this.unitat = unitat;
    }

    public String getSeccio() {
        return seccio;
    }

    public void setSeccio(String seccio) {
        this.seccio = seccio;
    }

    public double getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(double quantitat) {
        this.quantitat = quantitat;
    }

    public double getPreu() { return preu; }

    public void setPreu(double preu) { this.preu = preu;}

    @Override
    public String toString() {
        return "ArticlesCompra{" +
                "descripcio='" + descripcio + '\'' +
                ", unitat='" + unitat + '\'' +
                ", seccio='" + seccio + '\'' +
                ", quantitat=" + quantitat +
                ", preu=" + preu +
                '}';
    }
} // End of the ArticlesCompra class
