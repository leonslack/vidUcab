package com.ucab.restful.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CustomMissingAttributeException extends CustomBaseException{
	

	private static final long serialVersionUID = -4340613613839109130L;

	public CustomMissingAttributeException(String message) {
		super(message);
		setHttpStatus(HttpStatus.BAD_REQUEST);
		
	}

}
