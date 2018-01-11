package com.ucab.restful.repository;

import java.util.List;

import com.ucab.restful.data.model.Video;

public interface PrivacyRepositoryCustom {
	
	List<Video> listVideosWithPrivacyLogic(String userId,String ownerId);

}
