package com.ucab.restful.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Comment;

public interface ICommentService {

	/**
	 * 
	 * @param comment
	 * @return Created comment
	 * @throws CustomBaseException
	 */
	@Transactional(rollbackFor = Exception.class)
	Comment createComment(Comment comment) throws CustomBaseException;
	
	/**
	 * 
	 * @param predicate
	 * @return List of comment filter by predicate
	 * @throws CustomBaseException
	 */
	@Transactional(readOnly = true)
	List<Comment> getComments(Predicate predicate) throws CustomBaseException;

}
