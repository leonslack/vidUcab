package com.ucab.restful.service;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;

public interface CRUDService<E> {

	@Transactional(rollbackFor = Exception.class)
	E create(E entity) throws CustomBaseException;

	@Transactional(readOnly=true)
	E findById(UUID id) throws CustomBaseException;

	@Transactional(readOnly=true)
	Page<E> getAll(Pageable pageable, Predicate predicate) throws CustomBaseException;

	@Transactional(rollbackFor = Exception.class)
	void delete(Serializable id) throws CustomBaseException;
}
