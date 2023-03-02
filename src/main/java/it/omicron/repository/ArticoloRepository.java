package it.omicron.repository;

import it.omicron.entities.Articoli;

import it.omicron.entities.Magazzino;
import it.omicron.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("articoloRepository")
public interface ArticoloRepository extends JpaRepository<Articoli, Integer> {

    Optional<Articoli> findByNomeArticolo(String nomeArticolo);

    Optional<Articoli> findById(Integer id);

    void deleteByNomeArticolo(String nomeArticolo);


}

 