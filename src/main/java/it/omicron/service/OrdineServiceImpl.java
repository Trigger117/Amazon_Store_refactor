package it.omicron.service;

import it.omicron.entities.*;
import it.omicron.exceptions.HttpEntityException;
import it.omicron.model.*;
import it.omicron.repository.*;
import it.omicron.utility.OrdineUtility;
import it.omicron.utility.PaginationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ordineService")
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private ArticoloRepository articoloRepository;

    @Autowired
    public UtenteRepository utenteRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ArticoliOrdineRepository articoliOrdineRepository;

    @Autowired
    MagazzinoRepository magazzinoRepository;

    @Autowired
    OrdineUtility ordineUtility;

    @Autowired
    PaginationUtility paginationUtility;

    @Override
    public Ordine insertArticoloOrdine(NewOrdineReq ordineReq) {

        Ordine ordine = new Ordine();
        ordine.setCodiceOrdine(ordineUtility.codiceOrdineGenerator());
        Optional<Utente> utenteOpt = utenteRepository.findByUsername(ordineReq.getUsernameCliente());
        if (!utenteOpt.isPresent()) {
            throw new UsernameNotFoundException("Username non valida.");
        } else {
            ordine.setUsername(utenteOpt.get().getUsername());
            ordine = ordineRepository.save(ordine);
        }

        ordine = ordineRepository.save(ordine);
        for (ArticoliOrdineModel articoliOrdineModel : ordineReq.getListaArticoli()) {

            Optional<Articoli> articoliOpt = articoloRepository.findByNomeArticolo(articoliOrdineModel.getNomeArticolo());
            Optional<Magazzino> giacenzaArticolo = magazzinoRepository.findByNomeArticolo(articoliOrdineModel.getNomeArticolo());
            if (articoliOpt.isPresent()) {
                if (giacenzaArticolo.isPresent()) {


                    Magazzino giacenza = giacenzaArticolo.get();
                    int differenza = giacenza.getGiacenza() - articoliOrdineModel.getQuantità();
                    if (differenza > 0) {
                        if (articoliOrdineModel.getQuantità() > 0) {

                            giacenzaArticolo.get().setGiacenza(differenza);
                            magazzinoRepository.save(giacenza);
                            Articoli articolo = articoliOpt.get();
                            ArticoliOrdine articoliOrdine = new ArticoliOrdine(articolo, ordine, articoliOrdineModel.getQuantità());
                            articoliOrdineRepository.save(articoliOrdine);

                            ordine.sommaTotale(articolo.getPrezzo(), articoliOrdineModel.getQuantità());
                        } else {
                            throw new HttpEntityException("Impossibile inserire una quantita pari o minore a 0 = (" + articoliOrdineModel.getQuantità() + ")", HttpStatus.FORBIDDEN);
                        }
                    } else {
                        throw new HttpEntityException("Giacenza non sufficiente = (" + giacenza.getGiacenza() + ")", HttpStatus.FORBIDDEN);
                    }
                } else {
                    throw new HttpEntityException("Articolo non presente in giacenza= (" + articoliOrdineModel.getNomeArticolo() + ")", HttpStatus.NOT_FOUND);
                }
            } else {
                throw new HttpEntityException("Nessun articolo trovato con nome = (" + articoliOrdineModel.getNomeArticolo() + ")", HttpStatus.NOT_FOUND);
            }

        }
        ordineRepository.save(ordine);

        entityManager.clear();

        return ordineRepository.findById(ordine.getId()).get();
    }

    @Transactional
    @Override
    public void deleteOrdine(String codiceOrdine) {
        Optional<Ordine> ordineOpt = ordineRepository.findByCodiceOrdine(codiceOrdine);
        if (!ordineOpt.isPresent()) {
            throw new HttpEntityException("Nessun ordine trovato con codiceOrdine = [" + codiceOrdine + "]", HttpStatus.NOT_FOUND);
        }
        Ordine ordine = ordineOpt.get();
        for (ArticoliOrdine articoloOrdine : ordine.getArticoliOrdine()) {

            Optional<Magazzino> giacenzaArticolo = magazzinoRepository.findByNomeArticolo(articoloOrdine.getArticolo().getNome_Articolo());
            giacenzaArticolo.get().setGiacenza(giacenzaArticolo.get().getGiacenza() + articoloOrdine.getQuantità());
            magazzinoRepository.save(giacenzaArticolo.get());
        }
        articoliOrdineRepository.deleteAll(ordineOpt.get().getArticoliOrdine());
        ordineRepository.delete(ordine);
    }


    @Transactional
    @Override
    public void updateOrdine(OrdineUpdateModel ordineUpdate) {

        Optional<Ordine> ordineOpt = ordineRepository.findByCodiceOrdine(ordineUpdate.getCodiceOrdine());
        if (!ordineOpt.isPresent()) {
            throw new HttpEntityException("Nessun ordine trovato con codiceOrdine = [" + ordineUpdate.getCodiceOrdine() + "]", HttpStatus.NOT_FOUND);
        }
        Ordine ordine = ordineOpt.get();


        Optional<Articoli> articoliOpt = articoloRepository.findByNomeArticolo(ordineUpdate.getNomeArticolo());
        if (!articoliOpt.isPresent()) {
            throw new HttpEntityException("Nessun articolo trovato con nome: " + ordineUpdate.getNomeArticolo(), HttpStatus.NOT_FOUND);//
        }
        Optional<Magazzino> giacenzaArticolo = magazzinoRepository.findByNomeArticolo(articoliOpt.get().getNome_Articolo());
        if (!giacenzaArticolo.isPresent()) {
            throw new HttpEntityException("Articolo non presente in giacenza= (" + articoliOpt.get().getNome_Articolo() + ")", HttpStatus.NOT_FOUND);
        }

        if (checker(ordine, ordineUpdate) != null) {

            if (ordineUpdate.getQuantità() == 0) {
                ordine.sottraiTotale(articoliOpt.get().getPrezzo(), checker(ordine, ordineUpdate).getQuantità());
                giacenzaArticolo.get().setGiacenza(checker(ordine, ordineUpdate).getQuantità() + giacenzaArticolo.get().getGiacenza());
                magazzinoRepository.save(giacenzaArticolo.get());
                articoliOrdineRepository.delete(checker(ordine, ordineUpdate));
                ordineRepository.saveAndFlush(ordine);

            } else if (ordineUpdate.getQuantità() > checker(ordine, ordineUpdate).getQuantità()) {

                if ((giacenzaArticolo.get().getGiacenza() - differenza(ordineUpdate.getQuantità(), checker(ordine, ordineUpdate).getQuantità())) > 0) {
                    giacenzaArticolo.get().setGiacenza(giacenzaArticolo.get().getGiacenza() - differenza(ordineUpdate.getQuantità(), checker(ordine, ordineUpdate).getQuantità()));
                    magazzinoRepository.save(giacenzaArticolo.get());
                    ordine.sommaTotale(articoliOpt.get().getPrezzo(), differenza(ordineUpdate.getQuantità(), checker(ordine, ordineUpdate).getQuantità()));
                    checker(ordine, ordineUpdate).setQuantità(ordineUpdate.getQuantità());
                    articoliOrdineRepository.save(checker(ordine, ordineUpdate));
                    ordineRepository.saveAndFlush(ordine);

                } else {
                    throw new HttpEntityException("Giacenza non sufficiente = (" + giacenzaArticolo.get().getGiacenza() + ")", HttpStatus.CONFLICT);
                }
            } else {
                if (ordineUpdate.getQuantità() < checker(ordine, ordineUpdate).getQuantità()) {

                    giacenzaArticolo.get().setGiacenza(giacenzaArticolo.get().getGiacenza() + differenza(ordineUpdate.getQuantità(), checker(ordine, ordineUpdate).getQuantità()));
                    magazzinoRepository.save(giacenzaArticolo.get());
                    ordine.sottraiTotale(articoliOpt.get().getPrezzo(), differenza(ordineUpdate.getQuantità(), checker(ordine, ordineUpdate).getQuantità()));
                    checker(ordine, ordineUpdate).setQuantità(ordineUpdate.getQuantità());
                    articoliOrdineRepository.save(checker(ordine, ordineUpdate));
                    ordineRepository.saveAndFlush(ordine);

                }
            }
        } else {
            // CASO 2 SE L'ARTICOLO NON è CONTENUTO AGGIUNGERLO
            if ((giacenzaArticolo.get().getGiacenza() - ordineUpdate.getQuantità()) > 0) {
                giacenzaArticolo.get().setGiacenza(giacenzaArticolo.get().getGiacenza() - ordineUpdate.getQuantità());
                magazzinoRepository.save(giacenzaArticolo.get());
                Articoli articolo = articoliOpt.get();
                ArticoliOrdine newArticoloOrdine = new ArticoliOrdine(articolo, ordine, ordineUpdate.getQuantità());
                articoliOrdineRepository.save(newArticoloOrdine);
                ordine.sommaTotale(articoliOpt.get().getPrezzo(), ordineUpdate.getQuantità());
                ordineRepository.saveAndFlush(ordine);

            } else {
                throw new HttpEntityException("Giacenza non sufficiente = (" + giacenzaArticolo.get().getGiacenza() + ")", HttpStatus.CONFLICT);
            }
        }
    }

    @Override
    public PaginationResponse findAllOrdini(Integer offset, Integer limit) {

        List<Ordine> ordine = ordineRepository.findAll();

        PaginationResponse response = paginationUtility.buildPaginatinatedResponse(ordine.stream().skip(offset).limit(limit).collect(Collectors.toList()), offset, (offset + limit), ordine.size(), "Ordini");
        return response;
    }

    @Override
    public List<Ordine> findAllByUsernameOrderByDateAsc(String username) {
        return ordineRepository.findAllByUsernameOrderByDateAsc(username);
    }

    @Override
    public Optional<Ordine> findByCodiceOrdine(String codiceOrdine) {
        return ordineRepository.findByCodiceOrdine(codiceOrdine);
    }

    @Override
    public Optional<Ordine> findByUsername(String username) {
        return ordineRepository.findByUsername(username);
    }

    public int differenza(int quantitaUpdate, int quantitaArticolo) {
        int quantitaUpdate1;

        if (quantitaUpdate > quantitaArticolo) {
            quantitaUpdate1 = quantitaUpdate - quantitaArticolo;
        } else {
            quantitaUpdate1 = quantitaArticolo - quantitaUpdate;
        }
        return quantitaUpdate1;
    }

    public ArticoliOrdine checker(Ordine articoliOrdine, OrdineUpdateModel ordineUpdate) {

        for (ArticoliOrdine articoli : articoliOrdine.getArticoliOrdine()) {
            if (articoli.getArticolo().getNome_Articolo().equals(ordineUpdate.getNomeArticolo())) {
                return articoli;
            }
        }
        return null;
    }
}
