package it.omicron.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "magazzino")
public class Magazzino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;


    @JoinColumn(name = "nome_articolo",insertable = false, updatable = false)
    private String nomeArticolo;

    @Column(name = "giacenza", nullable = false, length = 30)
    private int giacenza;

    public Magazzino(Integer id, String nomeArticolo, int giacenza) {
        this.id = id;
        this.nomeArticolo = nomeArticolo;
        this.giacenza = giacenza;
    }

    public Magazzino() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArticolo() {
        return nomeArticolo;
    }

    public void setNomeArticolo(String nomeArticolo) {
        this.nomeArticolo = nomeArticolo;
    }

    public int getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(int giacenza) {
        this.giacenza = giacenza;
    }
}
