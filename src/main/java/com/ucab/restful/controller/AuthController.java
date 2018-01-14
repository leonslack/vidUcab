package com.ucab.restful.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.User;
import com.ucab.restful.dto.request.AuthRequest;
import com.ucab.restful.dto.response.SimpleResponseStructure;
import com.ucab.restful.service.IUserService;

@RestController
@RequestMapping("/auth")
@Api(name = "Auth Services", description = "Services to manage authentication", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class AuthController extends CustomBaseController{
	
	Logger logger = LogManager.getLogger();
	
	private IUserService userService;
	
	@Autowired
	public AuthController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	@ApiMethod(description = "Service to authenticate an user", summary = "AUTHENTICATE")
	public ResponseEntity<SimpleResponseStructure<User>> addUser(@RequestBody AuthRequest authRequest) throws CustomBaseException {
		
		SimpleResponseStructure<User> response = new SimpleResponseStructure<>();
		
		 
		logger.info("Trying to authenticate user with nickname: " + authRequest.nickname);

		response.setData(userService.findToAuth(authRequest));
		return ResponseEntity.ok(response);
	}

}
