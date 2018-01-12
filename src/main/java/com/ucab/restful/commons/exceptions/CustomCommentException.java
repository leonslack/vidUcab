package com.ucab.restful.commons.exceptions;

import org.springframework.http.HttpStatus;

import com.ucab.restful.commons.enums.CustomResponseCode;

public class CustomCommentException extends CustomBaseException{


	private static final long serialVersionUID = -2151145751511759123L;
	
	public CustomCommentException(String message) {
		super(message);
		setHttpStatus(HttpStatus.BAD_REQUEST);
		setCode(CustomResponseCode.INVALID_ACTION);
		
	}

}
