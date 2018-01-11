package com.ucab.restful.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import com.ucab.restful.commons.enums.PrivacyType;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomDataBaseOperationException;
import com.ucab.restful.data.model.Privacy;
import com.ucab.restful.data.model.User;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.dto.request.CreateVideoRequest;
import com.ucab.restful.repository.PrivacyRepository;
import com.ucab.restful.repository.VideoRepository;

@Service("IVideoService")
public class VideoService implements IVideoService {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private PrivacyRepository privacyRepository;

	final static Logger log = LogManager.getLogger();

	@Override
	public Video createVideo(CreateVideoRequest videoResquest) throws CustomBaseException {

		List<User> users = videoResquest.users;

		Video video = videoResquest.video;

		if (video.getPrivacyType() == PrivacyType.ONLYSOME && (users.isEmpty() || users == null)) {
			video.setPrivacyType(PrivacyType.PRIVATE);
			video = persist(video);
		} else if (video.getPrivacyType() == PrivacyType.PUBLIC || video.getPrivacyType() == PrivacyType.PRIVATE) {
			video = persist(video);
		} else if (video.getPrivacyType() == PrivacyType.ONLYSOME && !users.isEmpty()) {

			final Video savedVideo = persist(video);
			List<Privacy> privacyOfVideo = users.parallelStream().map(user -> createPrivacy(savedVideo, user))
					.collect(Collectors.toList());
			savePrivacyUsers(privacyOfVideo);
			return savedVideo;
		}

		return video;
	}

	private Privacy createPrivacy(final Video video, User user) {
		Privacy privacy = new Privacy();
		privacy.setSubscriber(user);
		privacy.setVideo(video);
		return privacy;
	}

	private void savePrivacyUsers(List<Privacy> privacyOfVideo) throws CustomBaseException {
		try {
			privacyRepository.save(privacyOfVideo);
		} catch (Exception e) {
			log.error("Error trying save privacy of video \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException("Error trying save privacy of video\n Error: " + e.getMessage());
		}
	}

	private Video persist(Video video) throws CustomBaseException {
		try {
			return videoRepository.save(video);
		} catch (Exception e) {
			log.error("Error trying save video \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException("Error trying save video\n Error: " + e.getMessage());
		}
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
			throw new CustomDataBaseOperationException("Error while trying finding videos \n Error: " + e.getMessage());
		}
	}

	@Override
	public List<Video> ListVideosWithPrivacy(String userId, String ownerId) throws CustomBaseException {
		try {
			return privacyRepository.listVideosWithPrivacyLogic(userId, ownerId);
		} catch (Exception e) {
			log.error("Error while trying finding videos \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException("Error while trying finding videos \n Error: " + e.getMessage());
		}
	}

}
