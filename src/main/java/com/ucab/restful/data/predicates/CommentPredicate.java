package com.ucab.restful.data.predicates;

import java.util.UUID;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.data.model.QComment;

public class CommentPredicate {
	
	private CommentPredicate() {
	}
	
	public static Predicate videoIdEquals(UUID videoId){
		return QComment.comment.video.id.eq(videoId);
	}

}
