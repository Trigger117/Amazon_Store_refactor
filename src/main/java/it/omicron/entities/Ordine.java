package it.omicron.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ordine")
public class Ordine implements Serializable {

private static final long serialVersionUID = 2L;

        @Id
        @JsonIgnore
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @JoinColumn(name = "username")
        private String username;

    @JsonIgnore
    @Column(name = "Date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Date date;

        @OneToMany(mappedBy = "ordine")
        private List<ArticoliOrdine> articoliOrdine;

        @Column(name = "totale")
    private double totaleOrdine;

        @Column(name = "codice")
    private String codiceOrdine;

    public Ordine(Integer id, String username, Date date, List<ArticoliOrdine> articoliOrdine, double totaleOrdine, String codiceOrdine) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.articoliOrdine = articoliOrdine;
        this.totaleOrdine = totaleOrdine;
        this.codiceOrdine = codiceOrdine;
    }

    public Ordine() {

    }

    public List<ArticoliOrdine> getArticoliOrdine() {
        return articoliOrdine;
    }

    public void setArticoliOrdine(List<ArticoliOrdine> articoliOrdine) {
        this.articoliOrdine = articoliOrdine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotaleOrdine() {
        return totaleOrdine;
    }

    public void setTotaleOrdine(double totaleOrdine) {
        this.totaleOrdine = totaleOrdine;
    }

    public String getCodiceOrdine() {
        return codiceOrdine;
    }

    public void setCodiceOrdine(String codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    public void sommaTotale(double costoArticolo, int quantita){
        double costoTotale = costoArticolo * quantita;
        this.totaleOrdine += costoTotale;
        this.totaleOrdine = Math.round(this.totaleOrdine * 100.0)/100.0;
    }
    public void sottraiTotale(double costoArticolo, int quantita){
        double costoTotale = costoArticolo * quantita;
        this.totaleOrdine -= costoTotale;
        this.totaleOrdine = Math.round(this.totaleOrdine * 100.0)/100.0;
    }

}
