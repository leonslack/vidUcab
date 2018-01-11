package com.ucab.restful.commons.exceptions;

public class CustomMessageError {
	
	public String ES_message;
	public String EN_message;
	
	
	@Override
	public String toString() {
		return "CustomMessageError [" + (ES_message != null ? "ES_Message=" + ES_message + ", " : "")
				+ (EN_message != null ? "EN_Message=" + EN_message : "") + "]";
	}

}
