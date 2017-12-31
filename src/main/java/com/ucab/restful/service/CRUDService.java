package com.ucab.restful.service;

/**
 * @author BytesTree
 */
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.controller.UserController;

public interface CRUDService<E> {
	
	final static Logger logger = Logger.getLogger(UserController.class);

	E create(E entity) throws CustomBaseException;

	E findById(Serializable id) throws CustomBaseException;

	List<E> getAll() throws CustomBaseException;

	void delete(Serializable id) throws CustomBaseException;
}
