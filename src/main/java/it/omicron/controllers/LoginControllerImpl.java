package it.omicron.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.constants.SwaggerConstant;
import it.omicron.entities.Utente;
import it.omicron.exceptions.HttpEntityException;
import it.omicron.model.UserCredentials;
import it.omicron.security.JwtAuthenticationResponse;
import it.omicron.security.JwtTokenProvider;
import it.omicron.service.UtenteService;

@RestController
@RequestMapping(SwaggerConstant.BASE_PATH)
public class LoginControllerImpl implements LoginController{
	
    private final Logger logger = LoggerFactory.getLogger(LoginControllerImpl.class);

    @Autowired
	private UtenteService utenteService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public ResponseEntity<?> login(@RequestBody UserCredentials loginRequest){
		logger.info("login - START- IN {}", loginRequest);
		Optional<Utente> utenteOpt = utenteService.findByUsername(loginRequest.getUsername());
		if(!utenteOpt.isPresent()) {
			throw new HttpEntityException("Nessun utente trovato con nome = [" + loginRequest.getUsername() + "]", HttpStatus.NOT_FOUND);
		}
		if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(), utenteOpt.get().getPassword())) {
			throw new HttpEntityException("Password errata", HttpStatus.UNAUTHORIZED);
		}
		Utente ut = utenteOpt.get();
		String jwt = jwtTokenProvider.generateToken(ut);
		logger.info("login - END");
	    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, ut.getUsername(), AuthorityRolesConstants.getGrantedAuthorityFromRuolo(ut.getRuoliUtenti())));
	}
	
}
