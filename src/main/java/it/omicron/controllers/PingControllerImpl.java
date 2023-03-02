package it.omicron.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.constants.SwaggerConstant;

@RestController
@RequestMapping(SwaggerConstant.BASE_PATH)
public class PingControllerImpl {
    
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("PING", HttpStatus.OK);
    }

    @Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<String> userPing() {
        return ResponseEntity.ok().body("Ping for users");
    }

    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    public ResponseEntity<String> adminPing() {
        return ResponseEntity.ok().body("Ping for admins");
    }
}
