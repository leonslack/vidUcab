package com.ucab.restful.commons.exceptions;

import org.springframework.http.HttpStatus;

import com.ucab.restful.commons.enums.CustomResponseCode;

public abstract class CustomBaseException extends Exception{

	private static final long serialVersionUID = -6618697964649691337L;
	
	private HttpStatus httpStatus;
	protected CustomResponseCode code;
	private String[] messages;
	
	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String... messages) {
		this.messages = messages;
	}

	public CustomBaseException(String...message) {
		this.messages = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public CustomResponseCode getCode() {
		return code;
	}
}
