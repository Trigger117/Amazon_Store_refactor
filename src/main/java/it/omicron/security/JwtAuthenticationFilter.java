package it.omicron.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import it.omicron.constants.AuthorityRolesConstants;
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	 String path = request.getRequestURI();
         logger.info("Filtro validazione JWT all'url = [{}].", path);
         String token = getJWTFromRequest(request);
         if (StringUtils.hasText(token) && jwtTokenProvider.isTokenValid(token)){
             logger.info("Validazione token avvenuta con successo");
             String email = jwtTokenProvider.getUsernameFromJWT(token);
             Set<GrantedAuthority> abilitazioni = jwtTokenProvider.getRolesFromJWT(token);
             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null, abilitazioni);
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authentication);
         } else {
             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token non valido");
         }
         filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        if(		path.startsWith("/swagger-ui")		||
        		path.startsWith("/v3/api-docs")		||
        		path.equals("/api/v1/login") 		||
        		path.equals("/api/v1/ping")			||
        		path.equals("/api/v1/new-utente")) {
            logger.info("Validazione jwt non effettuata per l'url = [{}]", path);
            return true;
        }
        return false;
    }
    
    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AuthorityRolesConstants.HEADER_STRING);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AuthorityRolesConstants.TOKEN_PREFIX)) {
            return bearerToken.substring(AuthorityRolesConstants.TOKEN_PREFIX.length());
        } else if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }
        return null;
    }

}
