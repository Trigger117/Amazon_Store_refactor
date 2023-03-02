package it.omicron.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.omicron.constants.SwaggerConstant;
import it.omicron.entities.Articoli;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ArticoliController {

    @RequestMapping(
            value = SwaggerConstant.ARTICOLO_PATH_FIND_ALL,
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(
            summary = SwaggerConstant.ARTICOLO_FIND_ALL_SUMMARY,
            description = SwaggerConstant.ARTICOLO_FIND_ALL_DESCRIPTION)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = SwaggerConstant.ARTICOLO_FIND_ALL_OK_EXAMPLE))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
    public ResponseEntity<?> findAllArticoli(
            @RequestParam(value = "offset", required = true) Integer offset,
            @RequestParam(value = "limit", required = true) Integer limit) throws Exception;

    @RequestMapping(
            value = SwaggerConstant.ARTICOLO_PATH,
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(
            summary = SwaggerConstant.ARTICOLO_FIND_SUMMARY,
            description = SwaggerConstant.ARTICOLO_FIND_DESCRIPTION)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = SwaggerConstant.ARTICOLO_FIND_OK_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_BAD_REQUEST_EXAMPLE))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
    public ResponseEntity<?> findArticolo(
            @RequestParam(value = "nomeArticolo", required = true) String nomeArticolo) throws Exception;

    @RequestMapping(
            value = SwaggerConstant.ARTICOLO_PATH,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
            summary = SwaggerConstant.ARTICOLO_INSERT_SUMMARY,
            description = SwaggerConstant.ARTICOLO_INSERT_DESCRIPTION)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    examples = @ExampleObject(value = SwaggerConstant.GENERIC_OK_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_BAD_REQUEST_EXAMPLE))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_CONFLICT_EXAMPLE))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
    public ResponseEntity<?> insertArticolo(@RequestBody Articoli articolo) throws Exception;

    @RequestMapping(
            value = SwaggerConstant.ARTICOLO_PATH,
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
            summary = SwaggerConstant.ARTICOLO_UPDATE_SUMMARY,
            description = SwaggerConstant.ARTICOLO_UPDATE_DESCRIPTION)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    examples = @ExampleObject(value = SwaggerConstant.GENERIC_OK_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_BAD_REQUEST_EXAMPLE))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_NOT_FOUND_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_CONFLICT_EXAMPLE))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
    public ResponseEntity<?> updateArticolo(@RequestBody Articoli articolo) throws Exception;

    @RequestMapping(
            value = SwaggerConstant.ARTICOLO_PATH,
            method = RequestMethod.DELETE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
            summary = SwaggerConstant.ARTICOLO_REMOVE_SUMMARY,
            description = SwaggerConstant.ARTICOLO_REMOVE_DESCRIPTION)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    examples = @ExampleObject(value = SwaggerConstant.GENERIC_OK_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_BAD_REQUEST_EXAMPLE))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_UNAUTHORIZED_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_NOT_FOUND_EXAMPLE))),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_CONFLICT_EXAMPLE))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            examples = @ExampleObject(value = SwaggerConstant.GENERIC_INTERNAL_SERVER_ERROR_EXAMPLE)))})
    public ResponseEntity<?> removeArticolo(@RequestParam(value = "nomeArticolo", required = true) String nomeArticolo) throws Exception;


}
