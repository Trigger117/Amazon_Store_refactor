package it.omicron.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "articoli")

public class Articoli implements java.io.Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
   @JsonIgnore
    private Integer id;



    @Column(name = "nomeArticolo", nullable = false)
    private String nomeArticolo;
    @Column(name = "prezzo", nullable = false, precision = 10,scale = 2)
    private Double prezzo;
    @Column(name = "imageURL", nullable = false)
    private String imageURL;

    public Articoli() {

    }

    public Articoli(String nomeArticolo, Double prezzo,String imageURL) {
        this.nomeArticolo = nomeArticolo;
        this.prezzo = prezzo;
        this.imageURL=imageURL;

    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_Articolo() {
        return nomeArticolo;
    }

    public void setNome_Articolo(String nomeArticolo) {
        this.nomeArticolo = nomeArticolo;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

}
