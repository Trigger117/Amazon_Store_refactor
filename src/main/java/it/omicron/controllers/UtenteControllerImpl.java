package it.omicron.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.constants.SwaggerConstant;
import it.omicron.entities.RuoloUtente;
import it.omicron.entities.Utente;
import it.omicron.exceptions.HttpEntityException;
import it.omicron.model.PaginationResponse;
import it.omicron.model.UtenteResponse;
import it.omicron.service.RuoloUtenteService;
import it.omicron.service.UtenteService;
import it.omicron.utility.PaginationUtility;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(SwaggerConstant.BASE_PATH)
public class UtenteControllerImpl implements UtenteController {

    private static final Logger logger = LoggerFactory.getLogger(UtenteControllerImpl.class);

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private RuoloUtenteService ruoloService;

    @Autowired
    private PaginationUtility paginationUtility;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> findAllUtente(Integer offset, Integer limit) {
        logger.info("findAllUtente - START - IN offset = [{}], limit = [{}]", offset, limit);
        List<Utente> utenti = utenteService.findAll();
        List<UtenteResponse> utResponse = utenteService.creaListaRespone(utenti);
        PaginationResponse response = paginationUtility.buildPaginatinatedResponse(utResponse.stream().skip(offset).limit(limit).collect(Collectors.toList()), offset, (offset + limit), utResponse.size(), "utenti");
        logger.info("findAllUtente - END");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Secured({AuthorityRolesConstants.ROLE_USER})
    public ResponseEntity<?> findUtente(String username) {
        logger.info("findUtente - START - IN = [{}]", username);
        Optional<Utente> utenteOpt = utenteService.findByUsername(username);
        if (!utenteOpt.isPresent()) {
            throw new HttpEntityException("Nessun utente trovato con username = [" + username + "]", HttpStatus.NOT_FOUND);
        }
        logger.info("findAllUtente - END");
        return new ResponseEntity<>(utenteOpt.get(), HttpStatus.OK);
    }

    // Questo metodo non ha nessuna restrizione in quanto può essere invocato per
    // creare un nuovo utente con ruolo "Utente" in fase di registrazione, non è
    // possibile creare utenti Amministratore tramite API
    @NotBlank
    public ResponseEntity<?> insertUtente(Utente utente) {
        logger.info("insertUtente - START - IN utente = [{}]", utente);

        if (utente.getUsername().isBlank() || utente.getIndirizzo().isBlank() || utente.getNome().isBlank() || utente.getCognome().isBlank()) {
            throw new HttpEntityException("Uno o più campi sono vuoti", HttpStatus.FORBIDDEN);
        }
        Optional<Utente> utenteOpt = utenteService.findByUsername(utente.getUsername());
        if (utenteOpt.isPresent()) {
            throw new HttpEntityException("Utente già presente", HttpStatus.CONFLICT);
        }
        utente.setPassword(bCryptPasswordEncoder.encode(utente.getUsername()));
        utente.setRuoliUtenti(getRuoloUtente(AuthorityRolesConstants.USER));
        utenteService.save(utente);
        logger.info("insertUtente - END");
        return new ResponseEntity<>("Nuovo utente aggiunto", HttpStatus.OK);
    }

    // Questo metodo può essere invocato solo da un Amministratore per creare un altro
    // utente con privilegi di ADMIN.
    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> createAdmin(Utente utente) {
        logger.info("insertUtente - START - IN utente = [{}]", utente);

        if (utente.getUsername().isBlank() || utente.getIndirizzo().isBlank() || utente.getNome().isBlank() || utente.getCognome().isBlank()) {
            throw new HttpEntityException("Uno o più campi sono vuoti", HttpStatus.FORBIDDEN);
        }
        Optional<Utente> utenteOpt = utenteService.findByUsername(utente.getUsername());
        if (utenteOpt.isPresent()) {
            throw new HttpEntityException("Utente già presente", HttpStatus.CONFLICT);
        }
        utente.setPassword(bCryptPasswordEncoder.encode(utente.getUsername()));
        utente.setRuoliUtenti(getRuoloUtente(AuthorityRolesConstants.ADMIN));
        utenteService.save(utente);
        logger.info("insertUtente - END");
        return new ResponseEntity<>("Nuovo utente aggiunto", HttpStatus.OK);
    }

    private RuoloUtente getRuoloUtente(String ruolo) {
        return ruoloService.findByRuolo(ruolo);
    }

    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> updateUtente(Utente utente,Integer id) {
        logger.info("updateUtente - START - IN utente = [{}], IN id = [{}]", utente,id);

        if (utente.getUsername().isBlank() || utente.getIndirizzo().isBlank() || utente.getNome().isBlank() || utente.getCognome().isBlank()) {
            throw new HttpEntityException("Uno o più campi sono vuoti", HttpStatus.FORBIDDEN);
        }
        Optional<Utente> utenteOpt = utenteService.findById(id);
        if (!utenteOpt.isPresent()) {
            throw new HttpEntityException("Nessun utente trovato con id = [" + id + "]", HttpStatus.NOT_FOUND);
        }
        Utente oldUtente = utenteOpt.get();
        // se la password arriva null, la password resta invariata
        // se la password non è null, va aggiornata
        if (utente.getPassword() == null) {
            utente.setPassword(oldUtente.getPassword());
        } else {
            utente.setPassword(bCryptPasswordEncoder.encode(utente.getPassword()));
        }
        utente.setId(id);
        utente.setPassword(bCryptPasswordEncoder.encode(utente.getUsername()));
        utente.setStatoAttivo(oldUtente.getStatoAttivo());
        utente.setRuoliUtenti(oldUtente.getRuoliUtenti());


        utenteService.save(utente);
        logger.info("updateUtente - END");
        return new ResponseEntity<>("Utente aggiornato", HttpStatus.OK);
    }

    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> removeUtente(Integer id) {
        logger.info("removeUtente - START - IN utenteId = [{}]", id);
        Optional<Utente> utenteOpt = utenteService.findById(id);
        if (!utenteOpt.isPresent()) {
            throw new HttpEntityException("Nessun utente trovato con id = [" + id + "]", HttpStatus.NOT_FOUND);
        }
        // TODO: implementare il controllo se l'utente ha degli ordini già inseriti prima di procedere alla cancellazione
//		if(!utenteOpt.get().getOrdini().isEmpty()) {
//			throw new HttpEntityException("Impossibile rimuovere l'utente = [" + utenteId + "] perchè ha degli ordini", HttpStatus.CONFLICT);
//		}
        utenteService.deleteById(id);
        logger.info("removeUtente - END");
        return new ResponseEntity<>("Utente rimosso", HttpStatus.OK);
    }

}
