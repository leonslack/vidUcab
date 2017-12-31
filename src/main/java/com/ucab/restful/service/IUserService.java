package com.ucab.restful.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.User;

public interface IUserService extends CRUDService<User>{
	
	@Transactional(readOnly = true)
	List<User> findAllUsers(Predicate predicate) throws CustomBaseException;

}
