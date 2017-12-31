package com.ucab.restful.service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomDataBaseOperationException;
import com.ucab.restful.data.model.User;
import com.ucab.restful.repository.UserRepository;

@Service("IUserService")
public class UserService  implements IUserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User create(User entity) throws CustomBaseException {
		try {
			return userRepository.save(entity);
		} catch (Exception e) {
			logger.error("Error while trying to save new User \n Error: " + e.getMessage());
			logger.error("User info:" + entity.toString());
			throw new CustomDataBaseOperationException(
					"Error while trying to save new User \n Error: " + e.getMessage());
		}
	}

	@Override
	public User findById(UUID id) throws CustomBaseException {
		try {
			return userRepository.findById(id);
		} catch (Exception e) {
			logger.error("Error while trying finding user \n Error: " + e.getMessage());
			logger.error("User ID:" + id.toString());
			throw new CustomDataBaseOperationException(
					"Error while trying to save new User \n Error: " + e.getMessage());
		}
	}

	@Override
	public List<User> getAll() throws CustomBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable id) throws CustomBaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAllUsers(Predicate predicate) throws CustomBaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
