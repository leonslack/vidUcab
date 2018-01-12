package com.ucab.restful.data.predicates;

import java.util.UUID;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.enums.Category;
import com.ucab.restful.commons.enums.PrivacyType;
import com.ucab.restful.data.model.QVideo;

public class VideoPredicates {
	
	private VideoPredicates() {}
	
	public static Predicate videoPublic() {
		return QVideo.video.privacyType.eq(PrivacyType.PUBLIC);
	}

	public static Predicate myVideos(UUID ownerId) {
		return QVideo.video.owner.id.eq(ownerId);
	}
	
	public static Predicate publicVideos(UUID ownerId) {
		
		return QVideo.video.privacyType.eq(PrivacyType.PUBLIC).and(QVideo.video.owner.id.eq(ownerId));
		
	}
	
	public static Predicate categoryEquals(Category category){
		return QVideo.video.category.eq(category);
	}

}
