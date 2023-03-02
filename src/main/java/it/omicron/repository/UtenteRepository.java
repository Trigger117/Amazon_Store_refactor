package it.omicron.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.omicron.entities.Utente;

@Repository("utenteRepository")
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
	
	Optional<Utente> findByUsername(String username);
	Optional<Utente> findById(Integer id);
	Utente findByUsernameAndPassword(String username, String password);

}
