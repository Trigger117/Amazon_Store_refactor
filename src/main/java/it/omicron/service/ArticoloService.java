package it.omicron.service;

import it.omicron.entities.Articoli;

import java.util.List;
import java.util.Optional;

public interface ArticoloService {

    public Optional<Articoli> findByNomeArticolo(String nomeArticolo);

    public List<Articoli> findAll();

    public void save(Articoli articolo);

    public Optional<Articoli> findById(Integer articoloId);

    public void deleteById(Integer articoloId);
    public void deleteByNomeArticolo(String nomeArticolo);

}
