package it.omicron.model;

import it.omicron.entities.RuoloUtente;

public class UtenteResponse implements java.io.Serializable {
	
	private static final long serialVersionUID = 10L;

	private String nome;
	private String cognome;
	private String username;
	private String indirizzo;
	private RuoloUtente ruoloUtente;
	private Boolean statoAttivo;

	public UtenteResponse() {
	}

	public UtenteResponse(String username, String nome, String cognome, String indirizzo, Boolean statoAttivo, RuoloUtente ruoloUtente) {
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.statoAttivo = statoAttivo;
		this.ruoloUtente = ruoloUtente;
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

	public RuoloUtente getRuoloUtente() {
		return ruoloUtente;
	}

	public void setRuoloUtente(RuoloUtente ruoloUtente) {
		this.ruoloUtente = ruoloUtente;
	}

}
