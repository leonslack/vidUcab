package com.ucab.restful.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.User;
import com.ucab.restful.dto.request.AuthRequest;

public interface IUserService extends CRUDService<User>{
	
	@Transactional(readOnly = true)
	List<User> findAllUsers(Predicate predicate) throws CustomBaseException;
	
	@Transactional(readOnly = true)
	User findOneByPredicate(AuthRequest dto) throws CustomBaseException;
	
	@Transactional(rollbackFor = Exception.class)
	User updateUser(User updatedUser) throws CustomBaseException;
	
	

}
