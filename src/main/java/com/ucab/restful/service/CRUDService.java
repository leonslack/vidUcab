package com.ucab.restful.service;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;

public interface CRUDService<E> {

	E create(E entity) throws CustomBaseException;

	E findById(UUID id) throws CustomBaseException;

	Page<E> getAll(Pageable pageable, Predicate predicate) throws CustomBaseException;

	void delete(Serializable id) throws CustomBaseException;
}
