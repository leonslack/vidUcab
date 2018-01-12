package com.ucab.restful;

import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ucab.restful.commons.exceptions.CustomAuthException;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomMissingAttributeException;
import com.ucab.restful.commons.exceptions.CustomNotFoundException;
import com.ucab.restful.data.model.User;
import com.ucab.restful.data.predicates.UserPredicates;
import com.ucab.restful.dto.request.AuthRequest;
import com.ucab.restful.repository.UserRepository;
import com.ucab.restful.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTests {
	
	@Mock
	UserRepository repo;
	
	@InjectMocks
	UserService service;
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test(expected = CustomMissingAttributeException.class)
	public void testNullUser() throws CustomBaseException {
		User user = null;
		
		user = service.updateUser(user);
		
		
	}
	
	@Test(expected = CustomNotFoundException.class)
	public void testNotFoundUser() throws CustomBaseException {
		User user = new User();
		
		user.setId(UUID.randomUUID());
		
		user = service.updateUser(user);
		
		when(service.findById(user.getId())).thenReturn(null);
		
	}
	
	
	@Test(expected = CustomAuthException.class)
	public void testBadCredentials() throws CustomBaseException {
		AuthRequest user = new AuthRequest();
		
		user.nickname="leonsalck";
		
		user.password="1234";
		
		service.findOneByPredicate(user);
		
		when(repo.findOne(UserPredicates.autheq(user))).thenReturn(null);
		
	}
	
	
	
	
	
	

}