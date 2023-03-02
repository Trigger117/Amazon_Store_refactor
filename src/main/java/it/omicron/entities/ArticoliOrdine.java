package it.omicron.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "articoli_ordine")
public class ArticoliOrdine {

    @EmbeddedId
    @JsonIgnore
    private ArticoliOrdinePKId id;

    @ManyToOne
    @JoinColumn(name = "id_articolo", insertable = false, updatable = false)
    private Articoli articolo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_ordine", insertable = false, updatable = false)
    private Ordine ordine;

    @Column(name = "quantita", nullable = false, length = 30)
    private int quantita;


    public ArticoliOrdine(Articoli articolo, Ordine ordine, int quantita) {
        this.id = new ArticoliOrdinePKId(articolo.getId(), ordine.getId());
        this.articolo = articolo;
        this.quantita = quantita;
        this.ordine = ordine;
    }

    public ArticoliOrdine() {

    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public int getQuantità() {
        return quantita;
    }

    public void setQuantità(int quantita) {
        this.quantita = quantita;
    }

    public Articoli getArticolo() {
        return articolo;
    }

    public void setArticolo(Articoli articolo) {
        this.articolo = articolo;
    }


    public ArticoliOrdinePKId getId() {
        return id;
    }

    public void setId(ArticoliOrdinePKId id) {
        this.id = id;
    }
}
