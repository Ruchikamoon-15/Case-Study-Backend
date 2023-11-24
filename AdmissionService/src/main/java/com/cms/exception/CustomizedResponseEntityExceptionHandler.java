package com.cms.exception;
import java.time.LocalDate;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {


	@ExceptionHandler(value= {Exception.class})
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
      ExceptionResponse errorDetails = new ExceptionResponse(LocalDate.now(),
	  ex.getMessage(),
	  request.getDescription(false),
	  "HTTP 500");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(value= {AdmissionInvalidException.class})
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(AdmissionInvalidException ex, WebRequest request) {
	ExceptionResponse errorDetails = new ExceptionResponse(LocalDate.now(),
	ex.getMessage(),
	request.getDescription(false),
	"HTTP 404");	
    	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}	
}

