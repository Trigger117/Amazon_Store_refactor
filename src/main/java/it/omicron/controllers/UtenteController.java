package it.omicron.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.omicron.constants.SwaggerConstant;
import it.omicron.entities.Utente;

public interface UtenteController {

	@RequestMapping(
			value = SwaggerConstant.UTENTE_PATH_FIND_ALL, 
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	@Operation(
			summary = SwaggerConstant.UTENTE_FIND_ALL_SUMMARY, 
			description = SwaggerConstant.UTENTE_FIND_ALL_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.APPLICATION_JSON_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.UTENTE_FIND_ALL_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
							@ApiResponse(	responseCode = "500", description = "Internal Server Error", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
	public ResponseEntity<?> findAllUtente(
			@RequestParam(value="offset", required = true) Integer offset,
			@RequestParam(value="limit", required = true) Integer limit) throws Exception;
	
	@RequestMapping(
			value = SwaggerConstant.UTENTE_PATH, 
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	@Operation(
			summary = SwaggerConstant.UTENTE_FIND_SUMMARY, 
			description = SwaggerConstant.UTENTE_FIND_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.APPLICATION_JSON_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.UTENTE_FIND_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
							@ApiResponse(	responseCode = "500", description = "Internal Server Error", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
	public ResponseEntity<?> findUtente(
			@RequestParam(value="username", required = true) String username) throws Exception;
	

	@RequestMapping(
			value = SwaggerConstant.NEW_UTENTE_PATH, 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	@Operation(
			summary = SwaggerConstant.UTENTE_INSERT_SUMMARY, 
			description = SwaggerConstant.UTENTE_INSERT_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "400", description = "Bad Request", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_BAD_REQUEST_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
							@ApiResponse(	responseCode = "409", description = "Conflict", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_CONFLICT_EXAMPLE))),
							@ApiResponse(	responseCode = "500", description = "Internal Server Error", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
	public ResponseEntity<?> insertUtente(@RequestBody Utente utente) throws Exception;
	
	@RequestMapping(
			value = SwaggerConstant.UTENTE_PATH, 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	@Operation(
			summary = SwaggerConstant.ADMIN_INSERT_SUMMARY, 
			description = SwaggerConstant.ADMIN_INSERT_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "400", description = "Bad Request", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_BAD_REQUEST_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
							@ApiResponse(	responseCode = "409", description = "Conflict", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_CONFLICT_EXAMPLE))),
							@ApiResponse(	responseCode = "500", description = "Internal Server Error", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
	public ResponseEntity<?> createAdmin(@RequestBody Utente utente) throws Exception;
	
	@RequestMapping(
			value = SwaggerConstant.UTENTE_PATH, 
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	@Operation(
			summary = SwaggerConstant.UTENTE_UPDATE_SUMMARY, 
			description = SwaggerConstant.UTENTE_UPDATE_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "400", description = "Bad Request", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_BAD_REQUEST_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
							@ApiResponse(	responseCode = "404", description = "Not Found", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_NOT_FOUND_EXAMPLE))),
							@ApiResponse(	responseCode = "409", description = "Conflict", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_CONFLICT_EXAMPLE))),
							@ApiResponse(	responseCode = "500", description = "Internal Server Error", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
	public ResponseEntity<?> updateUtente(@RequestBody Utente utente,
	@RequestParam(value="id", required = true) Integer id)throws Exception;
	
	@RequestMapping(
			value = SwaggerConstant.UTENTE_PATH, 
			method = RequestMethod.DELETE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	@Operation(
			summary = SwaggerConstant.UTENTE_REMOVE_SUMMARY, 
			description = SwaggerConstant.UTENTE_REMOVE_DESCRIPTION)
	@ApiResponses(value = {	@ApiResponse(	responseCode = "200", description = "OK",
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_OK_EXAMPLE))),
							@ApiResponse(	responseCode = "400", description = "Bad Request", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_BAD_REQUEST_EXAMPLE))),
							@ApiResponse(	responseCode = "401", description = "Unauthorized", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
							@ApiResponse(	responseCode = "404", description = "Not Found", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_NOT_FOUND_EXAMPLE))),
							@ApiResponse(	responseCode = "409", description = "Conflict", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_CONFLICT_EXAMPLE))),
							@ApiResponse(	responseCode = "500", description = "Internal Server Error", 
											content = @Content(	mediaType = MediaType.TEXT_PLAIN_VALUE, 
											examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
	public ResponseEntity<?> removeUtente(@RequestParam(value="id", required = true) Integer id) throws Exception;
	
}
