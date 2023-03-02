package it.omicron.model;

public class ArticoliMagazzino {

    private Integer id;
    private int giacenza;

    public ArticoliMagazzino(Integer id, int giacenza) {
        this.id = id;
        this.giacenza = giacenza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(int giacenza) {
        this.giacenza = giacenza;
    }
}
