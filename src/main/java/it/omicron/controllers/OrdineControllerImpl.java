package it.omicron.controllers;

import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.constants.SwaggerConstant;
import it.omicron.entities.Ordine;
import it.omicron.exceptions.HttpEntityException;
import it.omicron.model.NewOrdineReq;
import it.omicron.model.OrdineUpdateModel;
import it.omicron.model.PaginationResponse;
import it.omicron.utility.PaginationUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.omicron.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Integer.valueOf;


@RestController
@RequestMapping(SwaggerConstant.BASE_PATH)
public class OrdineControllerImpl implements OrdineController {

    private static final Logger logger = LoggerFactory.getLogger(OrdineControllerImpl.class);
    @Autowired
    private OrdineService ordineService;
    @Autowired
    private PaginationUtility paginationUtility;

    @Override
    @Transactional
    public ResponseEntity<?> findOrdine(Integer offset, Integer limit,String username) throws Exception {
        logger.info("findOrdine - START - IN Ordine = [{}]", username);

        List<Ordine> ordineOpt = ordineService.findAllByUsernameOrderByDateAsc(username);
        PaginationResponse response = paginationUtility.buildPaginatinatedResponse(ordineOpt.stream().skip(offset).limit(limit).collect(Collectors.toList()), 0, (offset + limit), ordineOpt.size(), "ordini");

        logger.info("findOrdine - END");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<?> findAllOrdini(Integer offset, Integer limit) {
        logger.info("findAllOrdini - START - IN offset = [{}], limit = [{}]", offset, limit);
        logger.info("findAllOrdini - END");
        return new ResponseEntity<>(ordineService.findAllOrdini(offset,limit), HttpStatus.OK);
    }

    @Override
    @Secured({AuthorityRolesConstants.ROLE_USER})
    public ResponseEntity<?> insertArticoloOrdine(@RequestBody NewOrdineReq ordineReq){

        logger.info("InsertOrdine - START");
        Ordine ordine = ordineService.insertArticoloOrdine(ordineReq);
        logger.info("Ordine inserito - END");
        return new ResponseEntity<>(ordine, HttpStatus.OK);
    }

    @Transactional
    @Override
    @Secured({AuthorityRolesConstants.ROLE_USER})
    public ResponseEntity<?> removeOrdine(String codiceOrdine) {
        logger.info("removeUtente - START - IN ordine = [{}]", codiceOrdine);
       ordineService.deleteOrdine(codiceOrdine);
       logger.info("removeOrdine - END");
        return new ResponseEntity<>("Ordine rimosso", HttpStatus.OK);
    }

    //TODO ECCEZIONE ConcurrentModificationException quando alterni gli articoli da modificare
    @Transactional
    @Override
    public ResponseEntity<?> updateOrdine(OrdineUpdateModel ordineUpdate){
        logger.info("updateOrdine - START - ordineUpdate = [{}] ", ordineUpdate);
        ordineService.updateOrdine(ordineUpdate);
        logger.info("updateOrdine - END");
        return new ResponseEntity<>("Ordine aggiornato", HttpStatus.OK);
    }
}