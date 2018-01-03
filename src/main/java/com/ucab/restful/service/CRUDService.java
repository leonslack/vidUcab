package com.ucab.restful.service;

/**
 * @author BytesTree
 */
import java.io.Serializable;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.controller.UserController;

public interface CRUDService<E> {
	
	final static Logger logger = Logger.getLogger(UserController.class);

	E create(E entity) throws CustomBaseException;

	E findById(UUID id) throws CustomBaseException;

	Page<E> getAll(Pageable pageable, Predicate predicate) throws CustomBaseException;

	void delete(Serializable id) throws CustomBaseException;
}
