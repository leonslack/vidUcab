package com.ucab.restful.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.User;

public interface ISubscriptionService {
	
	Page<User> getUserByRelation(Pageable pageable, Predicate predicate) throws CustomBaseException;

}
