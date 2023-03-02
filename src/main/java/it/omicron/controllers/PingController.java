package it.omicron.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.omicron.constants.AuthorityRolesConstants;
import it.omicron.constants.SwaggerConstant;

public interface PingController {
	
    @RequestMapping(value = SwaggerConstant.PING_PATH, method = RequestMethod.GET)
    @Operation(
			summary = SwaggerConstant.PING_REQUEST_SUMMARY, 
			description = SwaggerConstant.PING_REQUEST_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.LOGIN_REQUEST_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE)))})
    public ResponseEntity<String> ping();

    @RequestMapping(value = SwaggerConstant.USER_PING_PATH, method = RequestMethod.GET)
    @Secured({AuthorityRolesConstants.ROLE_USER, AuthorityRolesConstants.ROLE_ADMIN})
    @Operation(
			summary = SwaggerConstant.USER_PING_REQUEST_SUMMARY, 
			description = SwaggerConstant.USER_PING_REQUEST_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.LOGIN_REQUEST_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE)))})
    public ResponseEntity<String> userPing();

    @RequestMapping(value = SwaggerConstant.ADMIN_PING_PATH, method = RequestMethod.GET)
    @Secured({AuthorityRolesConstants.ROLE_ADMIN})
    @Operation(
			summary = SwaggerConstant.ADMIN_PING_REQUEST_SUMMARY, 
			description = SwaggerConstant.ADMIN_PING_REQUEST_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.LOGIN_REQUEST_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE)))})
    public ResponseEntity<String> adminPing();
}
