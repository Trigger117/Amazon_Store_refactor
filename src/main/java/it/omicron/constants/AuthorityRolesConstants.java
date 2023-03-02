package it.omicron.constants;

import java.util.ArrayList;
import java.util.List;

import it.omicron.entities.RuoloUtente;

public class AuthorityRolesConstants {
	
	// JWT
    public static final String SECRET = "iL0veAm@zon";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
	
    // Ruoli Utenti
	public static final String ADMIN = "Amministratore";
	public static final String USER = "Utente";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_GUEST = "ROLE_GUEST";
	
	public static List<String> getGrantedAuthorityFromRuolo(RuoloUtente ruoloUt){
		List<String> grantedAuthorities = new ArrayList<String>();
		switch (ruoloUt.getRuolo()) {
		case ADMIN: {
			grantedAuthorities.add(ROLE_ADMIN);
			grantedAuthorities.add(ROLE_USER);
		} break;
		case USER: {
			grantedAuthorities.add(ROLE_USER);
		} break;

		default: grantedAuthorities.add(ROLE_GUEST);
		}
		return grantedAuthorities;
	}
}
