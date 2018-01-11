package com.ucab.restful.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomMessageError;

public class AbstractBaseResponseStructure {
	
private String[] message ;	
	
	private List<CustomMessageError> errors;	

	private HttpStatus httpStatus;
	
	public AbstractBaseResponseStructure() {
		super();	
		this.httpStatus = HttpStatus.OK;
		
	}	
	public AbstractBaseResponseStructure(CustomBaseException cbe){
		super();
		
	}

	public AbstractBaseResponseStructure(Exception e){
		super();
		
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}
	

	public List<CustomMessageError> getErrors() {
		return errors;
	}
	public void setErrors(List<CustomMessageError> errors) {
		this.errors = errors;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
