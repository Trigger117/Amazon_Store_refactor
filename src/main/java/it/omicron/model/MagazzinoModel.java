package it.omicron.model;

import it.omicron.entities.Articoli;

import java.util.List;

public class MagazzinoModel {
    private static final long serialVersionUID = 12L;
    private Integer id;
    private int giacenza;


    public MagazzinoModel(int id, int giacenza) {
        this.id = id;
        this.giacenza = giacenza;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(int giacenza) {
        this.giacenza = giacenza;
    }

}
