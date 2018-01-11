package com.ucab.restful.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.dto.request.CreateVideoRequest;

public interface IVideoService {
	
	@Transactional(rollbackFor = Exception.class)
	Video createVideo(CreateVideoRequest videoResquest) throws CustomBaseException;
	
	@Transactional(rollbackFor = Exception.class)
	String deleteVideo(Video video) throws CustomBaseException;
	
	@Transactional(readOnly = true)
	List<Video> listVideos(Predicate predicate) throws CustomBaseException;
	
	@Transactional(readOnly = true)
	List<Video> ListVideosWithPrivacy(String userId, String ownerId) throws CustomBaseException;

}
