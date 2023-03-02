package it.omicron.service;

import it.omicron.entities.Articoli;
import it.omicron.repository.ArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("articoloService")
public class ArticoloServiceImpl implements ArticoloService {

    @Autowired
    private ArticoloRepository articoloRepository;


    @Override
    public Optional<Articoli> findByNomeArticolo(String nomeArticolo) {
        return articoloRepository.findByNomeArticolo(nomeArticolo);
    }

    @Override
    public List<Articoli> findAll() {
        return articoloRepository.findAll();
    }

    @Override
    public void save(Articoli articolo) {
        articoloRepository.save(articolo);
    }

    @Override
    public Optional<Articoli> findById(Integer articoloId) {
        return articoloRepository.findById(articoloId);
    }

    @Override
    public void deleteById(Integer articoloId) {
articoloRepository.deleteById(articoloId);
    }

    @Override
    public void deleteByNomeArticolo(String nomeArticolo) {
       articoloRepository.deleteByNomeArticolo(nomeArticolo);
    }
}
