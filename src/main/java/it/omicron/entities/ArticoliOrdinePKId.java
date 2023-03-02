package it.omicron.entities;

import javax.persistence.Embeddable;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticoliOrdinePKId implements Serializable {

    private int id_articolo;
    private int id_ordine;

    public ArticoliOrdinePKId(int id_articolo, int id_ordine) {
        this.id_articolo = id_articolo;
        this.id_ordine = id_ordine;
    }

    public ArticoliOrdinePKId() {

    }

    public int getIdArticolo() {
        return id_articolo;
    }

    public void setIdArticolo(int id_articolo) {
        this.id_articolo = id_articolo;
    }

    public int getIdOrdine() {
        return id_ordine;
    }

    public void setIdOrdine(int id_ordine) {
        this.id_ordine = id_ordine;
    }



}

