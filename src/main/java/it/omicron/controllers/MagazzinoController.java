package it.omicron.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.omicron.constants.SwaggerConstant;
import it.omicron.entities.Articoli;
import it.omicron.entities.Magazzino;
import it.omicron.model.MagazzinoModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface MagazzinoController {


    @RequestMapping(
            value = SwaggerConstant.MAGAZZINO_PATH,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(
            summary = SwaggerConstant.MAGAZZINO_INSERT_SUMMARY,
            description = SwaggerConstant.MAGAZZINO_INSERT_DESCRIPTION)
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
    public ResponseEntity<?> insertGiacenza(@RequestBody Magazzino magazzino) throws Exception;
}
