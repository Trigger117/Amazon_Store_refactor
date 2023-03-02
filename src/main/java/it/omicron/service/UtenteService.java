package it.omicron.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.omicron.entities.Utente;
import it.omicron.model.UtenteResponse;

public interface UtenteService extends UserDetailsService {
	
	public Optional<Utente> findByUsername(String username);
	

	
	public List<Utente> findAll();
	
	public void save(Utente utente);
	
	public Optional<Utente> findById(Integer utenteId);
	
	public void deleteById(Integer utenteId);
	
	public List<UtenteResponse> creaListaRespone(List<Utente> utenti);
	
	public UtenteResponse creaRespone(Utente utente);
}
