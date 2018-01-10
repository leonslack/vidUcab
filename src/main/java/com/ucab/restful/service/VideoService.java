package com.ucab.restful.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomDataBaseOperationException;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.repository.VideoRepository;

@Service("IVideoService")
public class VideoService implements IVideoService{
	
	@Autowired
	VideoRepository videoRepository;
	
	final static Logger log = LogManager.getLogger();

	@Override
	public Video createVideo(Video video) throws CustomBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteVideo(Video video) throws CustomBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> listVideos(Predicate predicate) throws CustomBaseException {
		try {
			return Lists.newArrayList(videoRepository.findAll(predicate));
		} catch (Exception e) {
			log.error("Error while trying finding videos \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException(
					"Error while trying finding videos \n Error: " + e.getMessage());
		}
	}

}
