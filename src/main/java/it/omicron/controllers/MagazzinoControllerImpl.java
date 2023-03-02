package it.omicron.controllers;

import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.constants.SwaggerConstant;
import it.omicron.entities.Articoli;
import it.omicron.entities.Magazzino;
import it.omicron.exceptions.HttpEntityException;
import it.omicron.model.ArticoliOrdineModel;
import it.omicron.model.MagazzinoModel;
import it.omicron.repository.ArticoloRepository;
import it.omicron.repository.MagazzinoRepository;
import it.omicron.service.MagazzinoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(SwaggerConstant.BASE_PATH)
public class MagazzinoControllerImpl implements MagazzinoController {

    private static final Logger logger = LoggerFactory.getLogger(MagazzinoControllerImpl.class);

    @Autowired
    private MagazzinoService magazzinoService;


    @Override
    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> insertGiacenza(Magazzino magazzino) throws Exception {
        logger.info("insertGiacenza - START - IN magazzino = [{}]", magazzino);
        magazzinoService.insertGiacenza(magazzino);
        logger.info("insertGiacenza - END");
        return new ResponseEntity<>("Nuovo articolo aggiunto", HttpStatus.OK);
    }
}
