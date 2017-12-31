package com.ucab.restful.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.User;
import com.ucab.restful.data.predicates.UserPredicates;
import com.ucab.restful.service.IGoogleService;
import com.ucab.restful.service.IUserService;

@RestController
@RequestMapping("/users")
@Api(name = "User Services", description = "Services to manage Users", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class UserController {
	
	final static Logger logger = Logger.getLogger(UserController.class);
	
	private IUserService userService;
	
	private IGoogleService googleService;

	@Autowired
	public UserController(IUserService userService,IGoogleService googleService) {
		super();
		this.userService = userService;
		this.googleService = googleService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User newUser) throws CustomBaseException {
		newUser = userService.create(newUser);
		logger.debug("Added:: " + newUser);

		return ResponseEntity.status(HttpStatus.OK).body(newUser);
	}
	
	@RequestMapping(value="/drive/{id}",method = RequestMethod.GET)
	public ResponseEntity<List<String>> getNameFiles(@PathVariable("id") UUID id) throws CustomBaseException, IOException {
		List<String> result = new LinkedList<>();
		
		User user = userService.findById(id);
		if (user == null) {
			logger.debug("User with id " + id + " does not exists");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		result = googleService.fileNames(user);
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@RequestBody User newUser) throws CustomBaseException {
		User existingEmp = userService.findById(newUser.getId());
		if (existingEmp == null) {
			logger.debug("User with id " + newUser.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.create(newUser);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") UUID id) throws CustomBaseException {
		User user = userService.findById(id);
		if (user == null) {
			logger.debug("User with id " + id + " does not exists");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found User: " + user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(
			@RequestParam(value = "keyword", defaultValue = "") final String keyword) throws CustomBaseException {
		List<User> Users = userService.findAllUsers(UserPredicates.recordContains(keyword));
		if (Users.isEmpty()) {
			logger.debug("Users does not exists");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + Users.size() + " Users");
		logger.debug(Arrays.toString(Users.toArray()));
		return new ResponseEntity<List<User>>(Users, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) throws CustomBaseException {
		User User = userService.findById(id);
		if (User == null) {
			logger.debug("User with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.delete(id);
			logger.debug("User with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
	
	
	

}
