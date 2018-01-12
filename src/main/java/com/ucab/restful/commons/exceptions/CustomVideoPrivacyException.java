package com.ucab.restful.commons.exceptions;

import org.springframework.http.HttpStatus;

import com.ucab.restful.commons.enums.CustomResponseCode;

public class CustomVideoPrivacyException extends CustomBaseException{

	private static final long serialVersionUID = 7948592792187908996L;
	
	public CustomVideoPrivacyException(String message) {
		super(message);
		setHttpStatus(HttpStatus.CONFLICT);
		setCode(CustomResponseCode.INVALID_ACTION);
		
	}

}
