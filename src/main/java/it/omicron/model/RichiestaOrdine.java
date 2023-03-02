package it.omicron.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.omicron.entities.Articoli;
import it.omicron.entities.ArticoliOrdine;
import it.omicron.entities.Utente;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RichiestaOrdine {

    private int totale;
    private int idUtente;
    private List<ArticoliOrdine> articoliOrdine;
    private Utente utente;
    private Articoli articolo;
    private int quantita;


    public RichiestaOrdine() {
    }

    public RichiestaOrdine(int totale, int idUtente, List<ArticoliOrdine> articoliOrdine,Utente utente,Articoli articolo,int quantita) {
        super();
        this.totale = totale;
        this.idUtente = idUtente;
        this.articoliOrdine =articoliOrdine;
        this.utente = utente;
        this.articolo=articolo;
        this.quantita=quantita;
    }

    public int getQuantità() {
        return quantita;
    }

    public void setQuantità(int quantita) {
        this.quantita = quantita;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Articoli getArticolo() {
        return articolo;
    }

    public void setArticolo(Articoli articolo) {
        this.articolo = articolo;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public List<ArticoliOrdine> getArticoliOrdine() {
        return articoliOrdine;
    }

    public void setArticoliOrdine(List<ArticoliOrdine> articoliOrdine) {
        this.articoliOrdine = articoliOrdine;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
}
