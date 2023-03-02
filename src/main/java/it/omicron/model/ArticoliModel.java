package it.omicron.model;

public class ArticoliModel {

    private String nomeArticolo;
    private Double prezzo;

    public ArticoliModel(String nomeArticolo, Double prezzo) {
        this.nomeArticolo = nomeArticolo;
        this.prezzo = prezzo;

    }

    public String getNomeArticolo() {
        return nomeArticolo;
    }

    public void setNomeArticolo(String nomeArticolo) {
        this.nomeArticolo = nomeArticolo;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }


}
