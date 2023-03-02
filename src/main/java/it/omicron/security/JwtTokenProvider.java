package it.omicron.security;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.entities.Utente;

@Component
public class JwtTokenProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    public static final long EXIPIRATION_TIME = 21_600_000; //6 ore

    public String generateToken(Utente utente) {
        return Jwts.builder()
                .setSubject(utente.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXIPIRATION_TIME))
                .claim("roles", AuthorityRolesConstants.getGrantedAuthorityFromRuolo(utente.getRuoliUtenti()))
                .claim("custom attribute", "Ciao")
                .signWith(SignatureAlgorithm.HS512, AuthorityRolesConstants.SECRET)
                .compact();
    }

    public boolean isTokenValid(String authToken) {
        try {
            Jwts.parser().setSigningKey(AuthorityRolesConstants.SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT Signature");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(AuthorityRolesConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    @SuppressWarnings("unchecked")
	public Set<GrantedAuthority> getRolesFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(AuthorityRolesConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();

        List<String> roles = (List<String>) claims.get("roles");
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String ruolo : roles) {
        	grantedAuthorities.add(new SimpleGrantedAuthority(ruolo));
        }
        return grantedAuthorities;
    }
}
