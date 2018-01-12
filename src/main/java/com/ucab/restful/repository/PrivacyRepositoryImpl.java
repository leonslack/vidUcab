package com.ucab.restful.repository;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ucab.restful.commons.enums.Category;
import com.ucab.restful.commons.enums.PrivacyType;
import com.ucab.restful.data.model.QPrivacy;
import com.ucab.restful.data.model.QVideo;
import com.ucab.restful.data.model.Video;

public class PrivacyRepositoryImpl implements PrivacyRepositoryCustom{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Video> listVideosWithPrivacyLogic(String userId, String ownerId, Category category) {
		
		JPQLQueryFactory base = new JPAQueryFactory(em);
		
		QPrivacy qprivacy = new QPrivacy("qprivacy");
		
		QVideo video = QVideo.video;
		
		JPQLQuery<Video> query = base.selectFrom(video);
		
		if(!userId.isEmpty() && !ownerId.isEmpty()) {
			query = query
					.leftJoin(video.privacy,qprivacy)
					.leftJoin(video.owner).fetchJoin()
					.where(video.owner.id.eq(UUID.fromString(ownerId))
							.and(video.privacyType.eq(PrivacyType.PUBLIC)
							.or(video.privacyType.eq(PrivacyType.ONLYSOME)
							.and(qprivacy.subscriber.id.eq(UUID.fromString(userId))))));
		}
		else if (!userId.isEmpty() && ownerId.isEmpty()) {
			query = query
					.leftJoin(video.privacy,qprivacy)
					.leftJoin(video.owner).fetchJoin()
					.where(video.owner.id.ne(UUID.fromString(userId))
							.and(video.privacyType.eq(PrivacyType.PUBLIC)
							.or(video.privacyType.eq(PrivacyType.ONLYSOME)
							.and(qprivacy.subscriber.id.eq(UUID.fromString(userId))))));
		}
		
		if(category!=null){
			return query.where(video.category.eq(category)).fetch();
		}
		
		return query.fetch();
	}

	@Override
	public Boolean userCanComment(UUID videoId, UUID userId) {
		
		JPQLQueryFactory query = new JPAQueryFactory(em);
		
		QPrivacy qprivacy = new QPrivacy("qprivacy");
		
		QVideo video = QVideo.video;
		return query.selectFrom(video)
				.leftJoin(video.privacy,qprivacy).where(video.id.eq(videoId)
						.and(video.privacyType.eq(PrivacyType.PUBLIC)
						.or(video.owner.id.eq(userId))
						.or(video.privacyType.eq(PrivacyType.ONLYSOME)
						.and(qprivacy.subscriber.id.eq(userId))))).fetchOne()!=null;
	}

}
