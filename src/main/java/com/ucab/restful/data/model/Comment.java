package com.ucab.restful.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;

@Entity
@Table(name = "[Comment]")
@ApiObject(name = "Comment", group = "Video", stage = ApiStage.RC)
public class Comment extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@ApiObjectField(description = "video", order = 10)
	@NotNull
	private Video video;
	
	@ApiObjectField(description = "Commenting User ", order = 20)
	@NotNull
	private User subscriber;
	
	@ApiObjectField(description = "Comment", order = 30)
	@NotNull
	private String text;
	
	@Column(name = "text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subscriber_id", nullable = false)
	public User getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(User subscriber) {
		this.subscriber = subscriber;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="video_id", nullable = false)
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}
