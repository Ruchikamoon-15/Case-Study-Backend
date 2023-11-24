package com.cms.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdmissionInvalidException extends Exception {
	
public AdmissionInvalidException(String message) {
	super(message);
}

public AdmissionInvalidException() {
	
}

}
