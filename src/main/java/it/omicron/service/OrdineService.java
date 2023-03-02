package it.omicron.service;


import it.omicron.entities.Articoli;
import it.omicron.entities.Ordine;
import it.omicron.model.NewOrdineReq;
import it.omicron.model.OrdineUpdateModel;
import it.omicron.model.PaginationResponse;

import java.util.List;
import java.util.Optional;


public interface OrdineService {

    Ordine insertArticoloOrdine(NewOrdineReq ordineReq);

    public void deleteOrdine(String codiceOrdine);

    void updateOrdine(OrdineUpdateModel ordineUpdate);

    PaginationResponse findAllOrdini(Integer offset, Integer limit);

List<Ordine> findAllByUsernameOrderByDateAsc(String username);

    public Optional<Ordine> findByCodiceOrdine(String codiceOrdine);
    Optional<Ordine> findByUsername(String username);
}


