package it.omicron.model;

import java.util.List;

public class OrdineUpdateModel {

    private static final long serialVersionUID = 11L;

    private String codiceOrdine;

    private String nomeArticolo;
    private int quantita;


    public OrdineUpdateModel(String codiceOrdine, String nomeArticolo, int quantita, int idOrdine) {
        this.codiceOrdine = codiceOrdine;
        this.nomeArticolo = nomeArticolo;
        this.quantita = quantita;

    }

    public String getCodiceOrdine() {
        return codiceOrdine;
    }

    public void setCodiceOrdine(String codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    public String getNomeArticolo() {
        return nomeArticolo;
    }

    public void setNomeArticolo(String nomeArticolo) {
        this.nomeArticolo = nomeArticolo;
    }

    public int getQuantità() {
        return quantita;
    }

    public void setQuantità(int quantita) {
        this.quantita = quantita;
    }

}
