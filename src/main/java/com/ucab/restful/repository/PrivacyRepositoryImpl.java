package com.ucab.restful.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ucab.restful.commons.enums.PrivacyType;
import com.ucab.restful.data.model.QPrivacy;
import com.ucab.restful.data.model.QVideo;
import com.ucab.restful.data.model.Video;

public class PrivacyRepositoryImpl implements PrivacyRepositoryCustom{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Video> listVideosWithPrivacyLogic(String userId, String ownerId) {
		JPQLQueryFactory query = new JPAQueryFactory(em);
		
		QPrivacy qprivacy = new QPrivacy("qprivacy");
		
		QVideo video = QVideo.video;
		
		List<Video> result = new LinkedList<>();
		
		if(!userId.isEmpty() && !ownerId.isEmpty()) {
			result = query.selectFrom(video)
					.leftJoin(video.privacy,qprivacy)
					.leftJoin(video.owner).fetchJoin()
					.where(video.owner.id.eq(UUID.fromString(ownerId))
							.and(video.privacyType.eq(PrivacyType.PUBLIC)
							.or(video.privacyType.eq(PrivacyType.ONLYSOME)
							.and(qprivacy.subscriber.id.eq(UUID.fromString(userId)))))).fetch();
		}
		else if (!userId.isEmpty() && ownerId.isEmpty()) {
			result = query.selectFrom(video)
					.leftJoin(video.privacy,qprivacy)
					.leftJoin(video.owner).fetchJoin()
					.where(video.privacyType.eq(PrivacyType.PUBLIC)
							.or(video.privacyType.eq(PrivacyType.ONLYSOME)
							.and(qprivacy.subscriber.id.eq(UUID.fromString(userId))))).fetch();
		}
		return result;
	}

}
