package com.ucab.restful.controller;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Comment;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.data.predicates.CommentPredicate;
import com.ucab.restful.dto.response.SimpleResponseStructure;
import com.ucab.restful.service.ICommentService;

@RestController
@RequestMapping("videos/{videoId}/comments")
@Api(name = "comments Services", description = "Services to manage comments", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class CommentController extends CustomBaseController {
	
	Logger logger = LogManager.getLogger();
	
	private ICommentService commentService;

	@Autowired
	public CommentController(ICommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SimpleResponseStructure<Comment>> addComment(@RequestBody Comment newComment,
			@PathVariable("videoId") UUID videoId) throws CustomBaseException {
		
		SimpleResponseStructure<Comment> response = new SimpleResponseStructure<>();
		
		Video v = new Video();
		
		v.setId(videoId);
		
		newComment.setVideo(v);
		newComment = commentService.createComment(newComment);
		logger.debug("Added:: " + newComment);

		response.setData(newComment);
		return ResponseEntity.ok(response);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<SimpleResponseStructure<List<Comment>>> getComments(
			@PathVariable("videoId") UUID videoId) throws CustomBaseException {
		
		SimpleResponseStructure<List<Comment>> response = new SimpleResponseStructure<>();
		
		List<Comment> result =commentService.getComments(CommentPredicate.videoIdEquals(videoId)); 
		
		result=result.stream().sorted(Comparator.comparing(Comment::getCreatedAt).reversed()).collect(Collectors.toList());
		response.setData(result);

		return ResponseEntity.ok(response);
	}
	
	

}
