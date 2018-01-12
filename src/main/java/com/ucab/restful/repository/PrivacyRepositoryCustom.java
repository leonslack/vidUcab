package com.ucab.restful.repository;

import java.util.List;
import java.util.UUID;

import com.ucab.restful.commons.enums.Category;
import com.ucab.restful.data.model.Video;

public interface PrivacyRepositoryCustom {
	
	List<Video> listVideosWithPrivacyLogic(String userId,String ownerId, Category category);
	
	Boolean userCanComment(UUID videoId, UUID userId);

}
