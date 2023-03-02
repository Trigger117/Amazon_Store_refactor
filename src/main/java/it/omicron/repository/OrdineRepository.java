package it.omicron.repository;

import it.omicron.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ordineRepository")
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {


     List<Ordine> findAllByUsernameOrderByDateAsc(String username);
     Optional<Ordine> findByCodiceOrdine(String username);
     Optional<Ordine> findByUsername(String username);


}
