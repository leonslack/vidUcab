package com.ucab.restful.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.User;
import com.ucab.restful.data.predicates.UserPredicates;
import com.ucab.restful.dto.response.PagedResponseStructure;
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
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User newUser) throws CustomBaseException {
		newUser = userService.create(newUser);
		logger.debug("Added:: " + newUser);

		return ResponseEntity.status(HttpStatus.OK).body(newUser);
	}
	
	@CrossOrigin(origins = "*")
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

	@CrossOrigin(origins = "*")
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

	@CrossOrigin(origins = "*")
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

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<PagedResponseStructure<User>> getAllUsers(
			@RequestParam(value = "keyword", defaultValue = "") final String keyword,
			@PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = { 
					User._createdAt }, direction = Sort.Direction.DESC) Pageable pageable) throws CustomBaseException {
		
		PagedResponseStructure<User> response = new PagedResponseStructure<>();
		
		Page<User> users = userService.getAll(pageable, UserPredicates.recordContains(keyword));
		
		response.setData(users);
		if (users.getContent() == null ||users.getContent().isEmpty()) {
			logger.debug("Users does not exists");
			return new ResponseEntity<PagedResponseStructure<User>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + users.getSize() + " Users");
		return new ResponseEntity<PagedResponseStructure<User>>(response, HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
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
