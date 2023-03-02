package it.omicron.repository;

import it.omicron.entities.Articoli;
import it.omicron.entities.Magazzino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("MagazzinoRepository")
public interface MagazzinoRepository extends JpaRepository<Magazzino, Integer> {





    Optional<Magazzino> findByNomeArticolo(String nomeArticolo);
    }
