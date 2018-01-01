package com.ucab.restful.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CustomAlreadyExistsException extends CustomBaseException {

	private static final long serialVersionUID = -4026503259763678458L;

	public CustomAlreadyExistsException(String message) {
		super(message);
		setHttpStatus(HttpStatus.BAD_REQUEST);
		
	}

}
