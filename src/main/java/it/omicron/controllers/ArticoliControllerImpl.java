package it.omicron.controllers;

import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.constants.SwaggerConstant;
import it.omicron.entities.Articoli;
import it.omicron.exceptions.HttpEntityException;
import it.omicron.model.PaginationResponse;
import it.omicron.service.ArticoloService;
import it.omicron.utility.PaginationUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(SwaggerConstant.BASE_PATH)

public class ArticoliControllerImpl implements ArticoliController {

    private static final Logger logger = LoggerFactory.getLogger(ArticoliControllerImpl.class);


    @Autowired
    private ArticoloService articoloService;

    @Autowired
    private PaginationUtility paginationUtility;

    @Override
    @Secured({AuthorityRolesConstants.ROLE_USER})
    public ResponseEntity<?> findAllArticoli(Integer offset, Integer limit){
        logger.info("findAllArticoli - START - IN offset = [{}], limit = [{}]", offset, limit);
        List<Articoli> articoli = articoloService.findAll();
        PaginationResponse response = paginationUtility.buildPaginatinatedResponse(articoli.stream().skip(offset).limit(limit).collect(Collectors.toList()), offset, (offset + limit), articoli.size(), "articoli");
        logger.info("findAllArticoli - END");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Secured({AuthorityRolesConstants.ROLE_USER})
    public ResponseEntity<?> findArticolo(String nomeArticolo){
        logger.info("findArticolo - START - IN = [{}]", nomeArticolo);
        Optional<Articoli> articoloOpt = articoloService.findByNomeArticolo(nomeArticolo);
        if (!articoloOpt.isPresent()) {
            throw new HttpEntityException("Nessun articolo trovato con username = [" + nomeArticolo + "]", HttpStatus.NOT_FOUND);
        }
        logger.info("findAllArticoli - END");
        return new ResponseEntity<>(articoloOpt.get(), HttpStatus.OK);
    }

    @Override
    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> insertArticolo(Articoli articolo) {
        logger.info("insertArticolo - START - IN articolo = [{}]", articolo);
        Optional<Articoli> articoloOpt = articoloService.findByNomeArticolo(articolo.getNome_Articolo());
        if (articoloOpt.isPresent()) {
            throw new HttpEntityException("Articolo già presente", HttpStatus.CONFLICT);
        }
        double a = Math.round(articolo.getPrezzo() * 100.0) / 100.0;
        if(a> 0.00) {
            articoloService.save(articolo);
        }else{
            throw new HttpEntityException("Valore negativo o troppo basso inserito = [" + articolo.getPrezzo() + "]", HttpStatus.FORBIDDEN);
        }
        logger.info("insertArticolo - END");
        return new ResponseEntity<>("Nuovo articolo aggiunto", HttpStatus.OK);
    }

    @Override
    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> updateArticolo(Articoli articolo){
        logger.info("updateArticolo - START - IN articolo = [{}],", articolo);
        Optional<Articoli> articoloOpt = articoloService.findByNomeArticolo(articolo.getNome_Articolo());
        if (!articoloOpt.isPresent()) {
            throw new HttpEntityException("Nessun articolo trovato con nome = [" + articolo.getNome_Articolo() + "]", HttpStatus.NOT_FOUND);
        }

        double a = Math.round(articolo.getPrezzo() * 100.0) / 100.0;
        if(a> 0.00) {
            articoloOpt.get().setPrezzo(articolo.getPrezzo());
            articoloOpt.get().setImageURL(articolo.getImageURL());
            articoloService.save(articoloOpt.get());

        }else{
            throw new HttpEntityException("Valore negativo o troppo basso inserito = [" + articolo.getPrezzo() + "]", HttpStatus.FORBIDDEN);
        }
        logger.info("updateArticolo - END");
        return new ResponseEntity<>("Articolo aggiornato", HttpStatus.OK);

    }

    @Override
    @Transactional
    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> removeArticolo(String nomeArticolo) {
        logger.info("removeArticolo - START - IN nomeArticolo = [{}]", nomeArticolo);
        Optional<Articoli> articoloOpt = articoloService.findByNomeArticolo(nomeArticolo);
        if (!articoloOpt.isPresent()) {
            throw new HttpEntityException("Nessun articolo trovato con nome = [" + nomeArticolo + "]", HttpStatus.NOT_FOUND);
        }
        articoloService.deleteByNomeArticolo(nomeArticolo);
        logger.info("removeArticolo - END");
        return new ResponseEntity<>("Articolo rimosso", HttpStatus.OK);
    }

}
