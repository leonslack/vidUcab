package com.ucab.restful.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
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

import com.ucab.restful.commons.enums.Category;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.data.predicates.VideoPredicates;
import com.ucab.restful.dto.request.CreateVideoRequest;
import com.ucab.restful.dto.response.SimpleResponseStructure;
import com.ucab.restful.service.IVideoService;

@RestController
@RequestMapping("/videos")
@Api(name = "Videos Services", description = "Services to manage videos", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class VideoController extends CustomBaseController {

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
	public ResponseEntity<SimpleResponseStructure<Video>> createVideo(@RequestBody CreateVideoRequest videoResquest)
			throws CustomBaseException {
		Video video = videoService.createVideo(videoResquest);

		SimpleResponseStructure<Video> response = new SimpleResponseStructure<>();

		response.setData(video);
		logger.debug("Added:: " + video);

		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	@ApiMethod(description = "Get list of videos", summary = "GET VIDEOS")
	public ResponseEntity<SimpleResponseStructure<List<Video>>> getvideos(
			@RequestParam(value = "ownerId", defaultValue = "", required = false) @ApiQueryParam(name = "ownerId", description = "video's owner id") final String ownerId,
			@RequestParam(value = "userId", defaultValue = "", required = false) @ApiQueryParam(name = "userId", description = "who is searching") final String userId,
			@RequestParam(value = "category", required = false) @ApiQueryParam(name = "category", description = "category to search") final Category category)
					throws CustomBaseException {

		SimpleResponseStructure<List<Video>> response = new SimpleResponseStructure<>();

		if (userId.isEmpty() && ownerId.isEmpty()) {
			response.setData(category == null ? videoService.listVideos(VideoPredicates.videoPublic())
					: videoService.listVideos(VideoPredicates.videoPublicAndCategory(category)));
		} else if (userId.isEmpty() && !ownerId.isEmpty()) {
			response.setData(category == null
					? videoService.listVideos(VideoPredicates.publicVideos(UUID.fromString(ownerId)))
					: videoService.listVideos(
							VideoPredicates.videoCategoryEqualsAndOwnerIdEquals(UUID.fromString(ownerId), category)));
		} else {
			response.setData(videoService.ListVideosWithPrivacy(userId, ownerId,category));
		}

		logger.debug("listing Videos from user: " + userId);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
