package com.example.spring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.spring.controller.FundTransferController;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(FundTransferController.class);

	@ExceptionHandler(NegativeAmountException.class)
	public ResponseEntity<String> handleNegativeAmount(NegativeAmountException negativeAmountException) {
		String errorMsg = negativeAmountException.getErrorMessage();
		errorLogging(negativeAmountException.getErrorCode(), errorMsg);
		return new ResponseEntity<String>(errorMsg, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<String> handleNoRecordFoundException(NoRecordFoundException noRecordFoundException) {
		String errorMsg = noRecordFoundException.getErrorMessage();
		errorLogging(noRecordFoundException.getErrorCode(), errorMsg);
		return new ResponseEntity<String>(errorMsg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OverdraftException.class)
	public ResponseEntity<String> handleOverdraftException(OverdraftException overdraftException) {
		String errorMsg = overdraftException.getErrorMessage();
		errorLogging(overdraftException.getErrorCode(), errorMsg);
		return new ResponseEntity<String>(errorMsg, HttpStatus.BAD_REQUEST);
	}
	
	private void errorLogging(String errorCode,String errorMessage) {
		LOGGER.error("[" + errorCode + "] " +errorMessage);
	}
}
