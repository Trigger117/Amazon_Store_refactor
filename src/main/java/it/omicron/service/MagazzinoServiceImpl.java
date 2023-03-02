package it.omicron.service;

import it.omicron.entities.Articoli;
import it.omicron.entities.Magazzino;
import it.omicron.exceptions.HttpEntityException;
import it.omicron.repository.ArticoloRepository;
import it.omicron.repository.MagazzinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service("magazzinoService")
public class MagazzinoServiceImpl implements MagazzinoService {



    @Autowired
    ArticoloRepository articoloRepository;
    @Autowired
    MagazzinoRepository magazzinoRepository;

    @Override
    public void insertGiacenza(Magazzino magazzino) {

        Optional<Articoli> articoloOpt = articoloRepository.findByNomeArticolo(magazzino.getNomeArticolo());
        if (!articoloOpt.isPresent()) {
            throw new HttpEntityException("Articolo non presente", HttpStatus.CONFLICT);
        }
        Optional<Magazzino> articoliMagazzino = magazzinoRepository.findByNomeArticolo(magazzino.getNomeArticolo());
        if (articoliMagazzino.isPresent()) {
            throw new HttpEntityException("Articolo già presente", HttpStatus.CONFLICT);
        }
        magazzinoRepository.save(magazzino);
    }
}