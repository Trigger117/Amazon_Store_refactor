package it.omicron.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "utenti", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Utente implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@JsonIgnore
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 30)
	private String nome;
	
	@Column(name = "cognome", nullable = false, length = 30)
	private String cognome;
	
	@Column(name = "username", unique = true, nullable = false, length = 20)
	private String username;
	
	@JsonIgnore
	@Column(name = "password", nullable = false, length = 20)
	private String password;
	
	@Column(name = "indirizzo", nullable = false, length = 100)
	private String indirizzo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ruolo", nullable = false)
	private RuoloUtente ruoliUtenti;
	
	@JsonIgnore
	@Column(name = "stato_attivo", nullable = false, columnDefinition = "boolean default true")
	private Boolean statoAttivo = true;
	
	@JsonIgnore
	@Column(name = "last_modify", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Generated(GenerationTime.INSERT)
	private Date lastModify;

	public Utente() {
	}

	public Utente(RuoloUtente ruoliUtenti, String username, String password) {
		this.ruoliUtenti = ruoliUtenti;
		this.username = username;
		this.password = password;
	}

	public Utente(RuoloUtente ruoliUtenti, String username, String nome, String cognome,
			String password, String indirizzo, Boolean statoAttivo) {
		this.ruoliUtenti = ruoliUtenti;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.indirizzo = indirizzo;
		this.statoAttivo = statoAttivo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RuoloUtente getRuoliUtenti() {
		return this.ruoliUtenti;
	}

	public void setRuoliUtenti(RuoloUtente ruoliUtenti) {
		this.ruoliUtenti = ruoliUtenti;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Boolean getStatoAttivo() {
		return this.statoAttivo;
	}

	public void setStatoAttivo(Boolean statoAttivo) {
		this.statoAttivo = statoAttivo;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

}
