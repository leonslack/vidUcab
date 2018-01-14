package com.ucab.restful.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.enums.Category;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.dto.request.CreateVideoRequest;

public interface IVideoService {
	
	/**
	 * 
	 * @param videoResquest
	 * @return created video
	 * @throws CustomBaseException
	 */
	@Transactional(rollbackFor = Exception.class)
	Video createVideo(CreateVideoRequest videoResquest) throws CustomBaseException;
	
	/**
	 * 
	 * @param video
	 * @return String with delete state
	 * @throws CustomBaseException
	 */
	@Transactional(rollbackFor = Exception.class)
	String deleteVideo(Video video) throws CustomBaseException;
	
	/**
	 * 
	 * @param predicate
	 * @return list of videos filter by predicate
	 * @throws CustomBaseException
	 */
	@Transactional(readOnly = true)
	List<Video> listVideos(Predicate predicate) throws CustomBaseException;
	
	/**
	 * 
	 * @param userId
	 * @param ownerId
	 * @param category
	 * @return list of videos with privacy rules 
	 * @throws CustomBaseException
	 */
	@Transactional(readOnly = true)
	List<Video> ListVideosWithPrivacy(String userId, String ownerId, Category category) throws CustomBaseException;

}
