package com.example.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// TODO: Log errors

	@ExceptionHandler(NegativeAmountException.class)
	public ResponseEntity<String> handleNegativeAmount(NegativeAmountException negativeAmountException){
		return new ResponseEntity<String>(negativeAmountException.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<String> handleNoRecordFoundException(NoRecordFoundException noRecordFoundException){
		return new ResponseEntity<String>(noRecordFoundException.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
