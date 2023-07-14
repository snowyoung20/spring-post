package com.example.springpost.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<RestApiException> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		RestApiException restApiException = new RestApiException(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(
				restApiException,
				HttpStatus.BAD_REQUEST
		);
	}
}
