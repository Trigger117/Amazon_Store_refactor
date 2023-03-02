package it.omicron.advices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.omicron.exceptions.HttpEntityException;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        logger.error("Unexpected error", ex);
        String bodyOfResponse = "Something unexpected happened";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    @ExceptionHandler(value = { AuthenticationException.class })
    protected ResponseEntity<Object> handleAuthenticationException(RuntimeException ex, WebRequest request) {
        logger.error("Authentication error", ex);
        String bodyOfResponse = "Authentication Error: "+ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
    
    @ExceptionHandler(value = { AccessDeniedException.class })
    protected ResponseEntity<Object> handleAccessDeniedExceptionException(RuntimeException ex, WebRequest request) {
        logger.error("Access denied", ex);
        String bodyOfResponse = "Accesso negato";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
    
    @ExceptionHandler(value = { HttpEntityException.class })
    protected ResponseEntity<Object> handleGetEntityExceptionException(HttpEntityException ex, WebRequest request) {
        logger.error("HttpEntityException: ", ex);
        return handleExceptionInternal(ex, ex.getDescription(), new HttpHeaders(), ex.getHttpStatus(), request);
    }
}
