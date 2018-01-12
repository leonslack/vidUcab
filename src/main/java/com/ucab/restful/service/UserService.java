package com.ucab.restful.service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomAlreadyExistsException;
import com.ucab.restful.commons.exceptions.CustomAuthException;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomDataBaseOperationException;
import com.ucab.restful.commons.exceptions.CustomMissingAttributeException;
import com.ucab.restful.commons.exceptions.CustomNotFoundException;
import com.ucab.restful.data.model.User;
import com.ucab.restful.data.predicates.UserPredicates;
import com.ucab.restful.dto.request.AuthRequest;
import com.ucab.restful.repository.UserRepository;

@Service("IUserService")
public class UserService  implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	final static Logger log = LogManager.getLogger();

	@Override
	public User create(User entity) throws CustomBaseException {
		User aux = findByNickname(entity.getNickname());
		if(aux != null) {
			throw new CustomAlreadyExistsException("This nickname is already in use");
		}
		
		validateEntity(entity);
		
		return persistUser(entity);
		
	}
	
	private void validateEntity(User user) throws CustomMissingAttributeException{
		if(user.getPassword()==null){
			log.info("Null attributes in user, password cant be null");
			throw new CustomMissingAttributeException("Null Attribute password in user");
		}
		if(user.getNickname()==null){
			log.info("Null attributes in user");
			throw new CustomMissingAttributeException("Null Attribute nickname in user");
		}
	}
	
	private User persistUser(User entity) throws CustomDataBaseOperationException{
		try {
			return userRepository.save(entity);
		} catch (Exception e) {
			log.error("Error while trying to save new User \n Error: " + e.getMessage());
			log.error("User info:" + entity.toString());
			throw new CustomDataBaseOperationException(
					"Error while trying to save new User \n Error: " + e.getMessage());
		}
	}
	
	private User findByNickname(String nick) throws CustomBaseException{
		try {
			return userRepository.findOne(UserPredicates.nickNameEquals(nick));
		} catch (Exception e) {
			log.error("Error while trying finding user \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException(
					"Error while trying to save new User \n Error: " + e.getMessage());
		}
	}

	@Override
	public User findById(UUID id) throws CustomBaseException {
		try {
			return userRepository.findById(id);
		} catch (Exception e) {
			log.error("Error while trying finding user \n Error: " + e.getMessage());
			log.error("User ID:" + id.toString());
			throw new CustomDataBaseOperationException(
					"Error while trying to find User \n Error: " + e.getMessage());
		}
	}

	@Override
	public Page<User> getAll(Pageable pageable, Predicate predicate) throws CustomBaseException {
		try {
			return userRepository.findAll(predicate, pageable);
		} catch (Exception e) {
			log.error("Error while trying find users \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException(
					"Error while trying to find new User \n Error: " + e.getMessage());
		}
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

	@Override
	public User findOneByPredicate(AuthRequest dto) throws CustomBaseException {
		User response;
		try {
			response = userRepository.findOne(UserPredicates.autheq(dto));
		} catch (Exception e) {
			log.error("Error while trying finding user to authenticate \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException(
					"Error while trying to finding new User to autenticate\n Error: " + e.getMessage());
		}
		
		if(response == null){
			throw new CustomAuthException("Nickname or password invalid");
		}
		return response;
	}

	@Override
	public User updateUser(User updatedUser) throws CustomBaseException {
		if (updatedUser == null || updatedUser.getId() == null) {
			log.info("Null attributes in user");
			throw new CustomMissingAttributeException("Null Attribute in user");
		}
		
		User queriedUser = this.findById(updatedUser.getId());
		
		if (queriedUser == null) {
			log.info("Given user not found");
			throw new CustomNotFoundException("Given user not found");
		}
		
		
		if(updatedUser.getPassword()!=null)
			queriedUser.setPassword(updatedUser.getPassword());
		if(updatedUser.getFirstName()!=null)
			queriedUser.setFirstName(updatedUser.getFirstName());
		if(updatedUser.getLastName()!=null)
			queriedUser.setLastName(updatedUser.getLastName());
		
		return persistUser(queriedUser);
	}

}
