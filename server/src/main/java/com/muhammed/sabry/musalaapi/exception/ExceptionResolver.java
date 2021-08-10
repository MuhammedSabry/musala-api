package com.muhammed.sabry.musalaapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class ExceptionResolver extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {InvalidArgumentException.class})
	protected ResponseEntity<Object> handleConflict(InvalidArgumentException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {NotFoundException.class})
	protected ResponseEntity<Object> handleConflict(NotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
