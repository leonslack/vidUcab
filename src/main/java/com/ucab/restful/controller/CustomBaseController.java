package com.ucab.restful.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.dto.response.AbstractBaseResponseStructure;
import com.ucab.restful.dto.response.ExceptionWrapper;
import com.ucab.restful.dto.response.PagedResponseStructure;
import com.ucab.restful.dto.response.SimpleResponseStructure;

public class CustomBaseController {
	
	@ExceptionHandler(CustomBaseException.class)
	public ResponseEntity<AbstractBaseResponseStructure> customErrorHandler(HttpServletRequest req,
			CustomBaseException cbe) {

		SimpleResponseStructure<ExceptionWrapper> srs = new SimpleResponseStructure<ExceptionWrapper>();
		ExceptionWrapper ew = new ExceptionWrapper(cbe);
		srs.setData(ew);
		srs.setMessage(ew.messages);
		srs.setHttpStatus(cbe.getHttpStatus());
		return ResponseEntity.status(cbe.getHttpStatus()).body(srs);
	}

	/**
	 * Method that returns that returns the Response entity with the
	 * HttpStatusCode set in the {@linkplain AbstractBaseResponseStructure}
	 * 
	 * @param response
	 * @return
	 */
	public static <T extends AbstractBaseResponseStructure> ResponseEntity<T> getResponseEntity(T response) {
		HttpStatus status = response.getHttpStatus() == null ? HttpStatus.OK : response.getHttpStatus();

		return ResponseEntity.status(status).body(response);
	}

	/**
	 * Method that returns that returns the Response entity with the
	 * HttpStatusCode set in the {@linkplain AbstractBaseResponseStructure}
	 *
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T extends PagedResponseStructure> ResponseEntity<T> getResponseEntity(T response) {
		HttpStatus status = response.getHttpStatus() == null ? HttpStatus.OK : response.getHttpStatus();

		return ResponseEntity.status(status).body(response);
	}

}
