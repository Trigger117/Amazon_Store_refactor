package it.omicron.exceptions;

import org.springframework.http.HttpStatus;

public class HttpEntityException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	private String description;
	private HttpStatus httpStatus;
	
	public HttpEntityException() {
		super();
	}
	
	public HttpEntityException(Exception e) {
		super(e);
	}

	public HttpEntityException(String description, HttpStatus httpStatus) {
		super();
		this.description = description;
		this.httpStatus = httpStatus;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
