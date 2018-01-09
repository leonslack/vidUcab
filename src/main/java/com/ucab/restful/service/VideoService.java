package com.ucab.restful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.repository.VideoRepository;

@Service("IVideoService")
public class VideoService implements IVideoService{
	
	@Autowired
	VideoRepository videoRepository;

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
		// TODO Auto-generated method stub
		return null;
	}

}
