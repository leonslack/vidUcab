package com.ucab.restful.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CustomNotFoundException extends CustomBaseException{

	private static final long serialVersionUID = -2529125844732757956L;
	
	public CustomNotFoundException(String message) {
		super(message);
		setHttpStatus(HttpStatus.BAD_REQUEST);
		
	}

}
