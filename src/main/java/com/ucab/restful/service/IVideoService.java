package com.ucab.restful.service;

import java.util.List;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.dto.request.CreateVideoRequest;

public interface IVideoService {
	
	Video createVideo(CreateVideoRequest videoResquest) throws CustomBaseException;
	
	String deleteVideo(Video video) throws CustomBaseException;
	
	List<Video> listVideos(Predicate predicate) throws CustomBaseException;

}
