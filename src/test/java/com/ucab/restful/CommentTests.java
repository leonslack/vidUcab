package com.ucab.restful;

import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomMissingAttributeException;
import com.ucab.restful.commons.exceptions.CustomVideoPrivacyException;
import com.ucab.restful.data.model.Comment;
import com.ucab.restful.data.model.User;
import com.ucab.restful.data.model.Video;
import com.ucab.restful.repository.PrivacyRepository;
import com.ucab.restful.service.CommentService;

@RunWith(MockitoJUnitRunner.class)
public class CommentTests {

	@Mock
	PrivacyRepository privacyRepository;

	@InjectMocks
	CommentService service;

	@Test(expected = CustomVideoPrivacyException.class)
	public void testCreateComment() throws CustomBaseException {
		Video video = new Video();
		video.setId(UUID.randomUUID());
		User subscriber = new User();
		subscriber.setId(UUID.randomUUID());
		Comment comment = new Comment();
		comment.setVideo(video);
		comment.setSubscriber(subscriber);

		when(privacyRepository.userCanComment(video.getId(), subscriber.getId())).thenReturn(false);

		service.createComment(comment);
	}
	
	@Test(expected = CustomMissingAttributeException.class)
	public void testCreateEmptyComment() throws CustomBaseException {
		Video video = new Video();
		video.setId(UUID.randomUUID());
		User subscriber = new User();
		subscriber.setId(UUID.randomUUID());
		Comment comment = new Comment();
		comment.setVideo(video);
		comment.setSubscriber(subscriber);
		
		comment.setText("");

		when(privacyRepository.userCanComment(video.getId(), subscriber.getId())).thenReturn(true);

		service.createComment(comment);
	}
	
	

}
