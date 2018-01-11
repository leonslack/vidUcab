package com.ucab.restful.dto.request;

import java.util.List;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.ucab.restful.data.model.User;
import com.ucab.restful.data.model.Video;

@ApiObject(name="CreateVideoRequest",group="VIDEO",description="Object used to create an video")
public class CreateVideoRequest {
	
	@ApiObjectField(order=0,required=true, description="video to persiste in DB")
	public Video video;
	
	@ApiObjectField(order=10,required=true, description="users to privacy")
	public List<User> users;

}
