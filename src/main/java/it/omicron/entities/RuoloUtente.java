package it.omicron.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * RuoliUtenti generated by hbm2java
 */
@Entity
@Table(name = "ruoli_utenti", uniqueConstraints = @UniqueConstraint(columnNames = "ruolo"))
public class RuoloUtente implements Serializable {
	
	private static final long serialVersionUID = 2L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "ruolo", unique = true, nullable = false, length = 20)
	private String ruolo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_modify", nullable = false)
	private Utente utenti;
	
	@JsonIgnore
	@Column(name = "last_modify")
	private Date lastModify;

	public RuoloUtente() {
	}

	public RuoloUtente(String ruolo) {
		this.ruolo = ruolo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Utente getUtenti() {
		return utenti;
	}

	public void setUtenti(Utente utenti) {
		this.utenti = utenti;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

}
