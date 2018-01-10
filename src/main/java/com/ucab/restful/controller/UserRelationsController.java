package com.ucab.restful.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.data.predicates.VideoPredicates;
import com.ucab.restful.dto.response.SimpleResponseStructure;
import com.ucab.restful.service.IVideoService;

@RestController
@RequestMapping("/users/{userId}")
@Api(name = "User Relations Services", description = "Services to manage relations of Users", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class UserRelationsController extends CustomBaseController{
	
	Logger logger = LogManager.getLogger();

	private IVideoService videoService;

	@Autowired
	public UserRelationsController(IVideoService videoService) {
		super();
		this.videoService = videoService;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/videos",method = RequestMethod.GET)
	@ApiMethod(description = "Get list of my videos", summary = "GET MY VIDEOS")
	public ResponseEntity<SimpleResponseStructure<List<Video>>> getMyVideos(
			@PathVariable("userId") @ApiPathParam(name = "userId", description = "The ID of the client") UUID userId)
			throws CustomBaseException {

		SimpleResponseStructure<List<Video>> response = new SimpleResponseStructure<>();

		response.setData(videoService.listVideos(VideoPredicates.myVideos(userId)));
		logger.debug("listing Videos from user: " + userId);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
