package com.ucab.restful.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CustomDataBaseOperationException extends CustomBaseException {

	private static final long serialVersionUID = -5193273160717451367L;
	
	public CustomDataBaseOperationException(String message) {
		super(message);
		setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

}
