package it.polito.esc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;

import it.polito.esc.service.response.EscErrorResponse;
import it.polito.esc.service.response.GenericResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<GenericResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		logger.error(ex.getMessage(), ex);
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		genericResponse.setEscErrorResponse(new EscErrorResponse("bad_request", ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<GenericResponse> handleJsonParseException(JsonParseException ex) {
		logger.error(ex.getMessage(), ex);
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		genericResponse.setEscErrorResponse(new EscErrorResponse("bad_request", ex.getOriginalMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<GenericResponse> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		logger.error(ex.getMessage(), ex);
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		genericResponse.setEscErrorResponse(new EscErrorResponse("bad_request", ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponse> handleException(Exception ex) {
		logger.error(ex.getMessage(), ex);
		ResponseEntity<GenericResponse> response = null;

		while (ex.getCause() != null) {
			ex = (Exception) ex.getCause();
			response = setResponse(ex, response);
		}

		if (response != null)
			return response;

		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		genericResponse.setEscErrorResponse(new EscErrorResponse("generic_error", ex.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
	}

	private ResponseEntity<GenericResponse> setResponse(Exception ex, ResponseEntity<GenericResponse> response) {
		if (ex instanceof IllegalArgumentException)
			response = handleIllegalArgumentException((IllegalArgumentException) ex);
		else if (ex instanceof JsonParseException)
			response = handleJsonParseException((JsonParseException) ex);
		else if (ex instanceof MissingServletRequestParameterException)
			response = handleMissingServletRequestParameterException((MissingServletRequestParameterException) ex);
		return response;
	}

}
