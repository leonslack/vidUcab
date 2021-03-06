package com.ucab.restful.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.ucab.restful.dto.response.SimpleResponseStructure;
import com.ucab.restful.service.IGoogleService;
import com.ucab.restful.service.IUserService;

@RestController
@RequestMapping("/users")
@Api(name = "User Services", description = "Services to manage Users", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class UserController extends CustomBaseController{
	
	Logger logger = LogManager.getLogger();
	
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
	public ResponseEntity<SimpleResponseStructure<User>> addUser(@RequestBody User newUser) throws CustomBaseException {
		
		SimpleResponseStructure<User> response = new SimpleResponseStructure<>();
		
		newUser = userService.create(newUser);
		logger.debug("Added:: " + newUser);

		response.setData(newUser);
		return ResponseEntity.ok(response);
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
		
		return ResponseEntity.ok(result);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<SimpleResponseStructure<User>> updateUser(@RequestBody User updatedUser) throws CustomBaseException {
		
		SimpleResponseStructure<User> response = new SimpleResponseStructure<>();
		
		response.setData(userService.updateUser(updatedUser));
		
		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SimpleResponseStructure<User>> getUser(@PathVariable("id") UUID id) throws CustomBaseException {
		
		SimpleResponseStructure<User> response = new SimpleResponseStructure<>();
		
		User user = userService.findById(id);
		
		if (user == null) {
			logger.debug("User with id " + id + " does not exists");
		}
		
		response.setData(user);
		
		logger.debug("Found User: " + user);
		return ResponseEntity.ok(response);
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
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		logger.debug("Found " + users.getSize() + " Users");
		return ResponseEntity.ok(response);
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
