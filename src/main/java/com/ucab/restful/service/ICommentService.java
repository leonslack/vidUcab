package com.ucab.restful.service;

import java.util.List;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Comment;

public interface ICommentService {
	
	Comment createComment(Comment comment) throws CustomBaseException;
	
	List<Comment> getComments(Predicate predicate) throws CustomBaseException;

}
