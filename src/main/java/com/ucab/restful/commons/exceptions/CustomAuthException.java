package com.ucab.restful.commons.exceptions;

import org.springframework.http.HttpStatus;

import com.ucab.restful.commons.enums.CustomResponseCode;

public class CustomAuthException extends CustomBaseException{

	private static final long serialVersionUID = 6907447708600317627L;
	
	public CustomAuthException(String message) {
		super(message);
		setHttpStatus(HttpStatus.BAD_REQUEST);
		setCode(CustomResponseCode.BAD_CREDENTIALS);
		
	}

}
