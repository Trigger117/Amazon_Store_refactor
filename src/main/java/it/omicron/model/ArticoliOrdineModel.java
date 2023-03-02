package it.omicron.model;

import it.omicron.entities.Articoli;
import it.omicron.entities.Ordine;

public class ArticoliOrdineModel {

    private String nome_Articolo;
    private int quantita;



    public ArticoliOrdineModel(String nome_Articolo, int quantita) {
        this.nome_Articolo = nome_Articolo;
        this.quantita = quantita;
    }

    public String getNomeArticolo() {
        return nome_Articolo;
    }

    public void setNomeArticolo(String nome_Articolo) {
        this.nome_Articolo = nome_Articolo;
    }

    public int getQuantità() {
        return quantita;
    }

    public void setQuantità(int quantita) {
        this.quantita = quantita;
    }


}
