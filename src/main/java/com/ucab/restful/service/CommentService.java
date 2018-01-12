package com.ucab.restful.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomDataBaseOperationException;
import com.ucab.restful.commons.exceptions.CustomMissingAttributeException;
import com.ucab.restful.commons.exceptions.CustomVideoPrivacyException;
import com.ucab.restful.data.model.Comment;
import com.ucab.restful.repository.CommentRepository;
import com.ucab.restful.repository.PrivacyRepository;

@Service("ICommentService")
public class CommentService implements ICommentService {
	
	@Autowired
	private PrivacyRepository privacyRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	final static Logger log = LogManager.getLogger();

	@Override
	public Comment createComment(Comment comment) throws CustomBaseException {
		validateEntity(comment);
		return persist(comment);
	}
	
	private void validateEntity(Comment comment) throws CustomBaseException{
		
		if(!privacyRepository.userCanComment(comment.getVideo().getId(), comment.getSubscriber().getId())){
			throw new CustomVideoPrivacyException("given User cant comment this video");
		}
		if(comment.getText()==null || comment.getText().isEmpty()){
			throw new CustomMissingAttributeException("Text of comment cant be empty");
		}
	}
	
	private Comment persist(Comment comment)throws CustomBaseException {
		try {
			return commentRepository.save(comment);
		} catch (Exception e) {
			log.error("Error while trying to save new comment \n Error: " + e.getMessage());
			log.error("comment info:" + comment.toString());
			throw new CustomDataBaseOperationException(
					"Error while trying to save new comment \n Error: " + e.getMessage());
		}
	}

	@Override
	public List<Comment> getComments(Predicate predicate) throws CustomBaseException {
		try {
			return Lists.newArrayList(commentRepository.findAll(predicate));
		} catch (Exception e) {
			log.error("Error while trying to get comments \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException(
					"Error while trying to get comments \n Error: " + e.getMessage());
		}
	}

}
