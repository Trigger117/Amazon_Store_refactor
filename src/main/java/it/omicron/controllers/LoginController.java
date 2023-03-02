package it.omicron.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.omicron.constants.SwaggerConstant;
import it.omicron.exceptions.HttpEntityException;
import it.omicron.model.UserCredentials;

public interface LoginController {

	@RequestMapping(
			value = SwaggerConstant.LOGIN_PATH, 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	@Operation(
			summary = SwaggerConstant.LOGIN_REQUEST_SUMMARY, 
			description = SwaggerConstant.LOGIN_REQUEST_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.LOGIN_REQUEST_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
							@ApiResponse(	responseCode = "404", description = "Not Found", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_NOT_FOUND_EXAMPLE))),
							@ApiResponse(	responseCode = "500", description = "Internal Server Error", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
	ResponseEntity<?> login(@RequestBody UserCredentials loginRequest) throws Exception, HttpEntityException;
		
}
