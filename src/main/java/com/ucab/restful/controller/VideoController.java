package com.ucab.restful.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.data.predicates.VideoPredicates;
import com.ucab.restful.dto.response.SimpleResponseStructure;
import com.ucab.restful.service.IVideoService;

@RestController
@RequestMapping("/videos")
@Api(name = "Videos Services", description = "Services to manage videos", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class VideoController {

	Logger logger = LogManager.getLogger();

	private IVideoService videoService;

	@Autowired
	public VideoController(IVideoService videoService) {
		super();
		this.videoService = videoService;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	@ApiMethod(description = "Cretate a new video", summary = "CREATE VIDEO")
	public ResponseEntity<SimpleResponseStructure<Video>> addSubcription(@RequestBody Video video)
			throws CustomBaseException {
		video = videoService.createVideo(video);

		SimpleResponseStructure<Video> response = new SimpleResponseStructure<>();

		response.setData(video);
		logger.debug("Added:: " + video);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	@ApiMethod(description = "Get list of videos", summary = "GET VIDEOS")
	public ResponseEntity<SimpleResponseStructure<List<Video>>> getvideos(
			@RequestParam(value = "ownerId", defaultValue = "") final String ownerId,
			@RequestParam(value = "userId", defaultValue="") final String userId)
			throws CustomBaseException {

		SimpleResponseStructure<List<Video>> response = new SimpleResponseStructure<>();
		
		if (userId.isEmpty() && ownerId.isEmpty()) {
			response.setData(videoService.listVideos(VideoPredicates.videoPublic()));
		}
		else if (!userId.isEmpty() && ownerId.isEmpty()) {
			//TODO feed public, and privacy with userID
		}
		else if(userId.isEmpty() && !ownerId.isEmpty()) {
			response.setData(videoService.listVideos(VideoPredicates.publicVideos(UUID.fromString(ownerId))));
		}
		else if(!userId.isEmpty() && !ownerId.isEmpty()) {
			//TODO feed 
		}

		
		logger.debug("listing Videos from user: " + userId);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	

}
