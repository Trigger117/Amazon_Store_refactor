package it.omicron.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.entities.RuoloUtente;
import it.omicron.entities.Utente;
import it.omicron.model.UtenteResponse;
import it.omicron.repository.UtenteRepository;

@Service("utenteService")
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<Utente> findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    public Utente findByUsernameAndPassword(String username, String password) {
        String encPassword = bCryptPasswordEncoder.encode(password);
        return utenteRepository.findByUsernameAndPassword(username, encPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utente> utente = utenteRepository.findByUsername(username);
        if (!utente.isPresent()) {
            throw new UsernameNotFoundException("Username non valida.");
        }
        Utente user = utente.get();
        if (!user.getStatoAttivo()) {
            throw new DisabledException("Utente disabilitato.");
        }
        Collection<String> roles = mapRoles(user.getRuoliUtenti());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(roles));
    }


    @Override
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    @Override
    public void save(Utente utente) {
        utenteRepository.save(utente);
    }

    @Override
    public Optional<Utente> findById(Integer utenteId) {
        return utenteRepository.findById(utenteId);
    }

    @Override
    public void deleteById(Integer utenteId) {
        utenteRepository.deleteById(utenteId);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Collection<String> mapRoles(RuoloUtente ruoloUtente) {
        switch (ruoloUtente.getRuolo()) {
            case AuthorityRolesConstants.USER:
                return Collections.singletonList(AuthorityRolesConstants.ROLE_USER);
            case AuthorityRolesConstants.ADMIN:
                return Arrays.asList(AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN);
            default:
                return new ArrayList<String>();
        }
    }

    @Override
    public List<UtenteResponse> creaListaRespone(List<Utente> utenti) {
        List<UtenteResponse> responseList = new ArrayList<UtenteResponse>();
        for (Utente utente : utenti) {
            responseList.add(creaRespone(utente));
        }
        return responseList;
    }

    @Override
    public UtenteResponse creaRespone(Utente utente) {
        return new UtenteResponse(
                utente.getUsername(),
                utente.getNome(),
                utente.getCognome(),
                utente.getIndirizzo(),
                utente.getStatoAttivo(),
                utente.getRuoliUtenti());
    }

}
