package it.omicron.repository;

import it.omicron.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("articoliOrdineRepository")
public interface ArticoliOrdineRepository extends JpaRepository<ArticoliOrdine, ArticoliOrdinePKId> {
    public Optional<ArticoliOrdine> findByQuantita(int quantita);


    Optional<ArticoliOrdine> findById(ArticoliOrdinePKId id);
}
