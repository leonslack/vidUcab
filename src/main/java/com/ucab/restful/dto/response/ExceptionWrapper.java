package com.ucab.restful.dto.response;

import org.springframework.http.HttpStatus;

import com.ucab.restful.commons.exceptions.CustomBaseException;


public class ExceptionWrapper {
	
	public HttpStatus httpStatus;
	public String[] messages;
	
	
	public ExceptionWrapper(CustomBaseException e){{
		
		this.httpStatus = e.getHttpStatus();
		this.messages = e.getMessages();
	}
		
	}

}
