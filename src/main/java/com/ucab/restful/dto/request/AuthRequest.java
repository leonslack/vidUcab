package com.ucab.restful.dto.request;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name="AuthRequest",group="Auth",description="Object used to auth an user")
public class AuthRequest {
	
	@ApiObjectField(order=0,required=true, description="nickname")
	public String nickname;
	
	@ApiObjectField(order=10,required=true, description="password")
	public String password;

}
