package com.ucab.restful.data.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

@Entity
@Table(name = "[Privacy]")
@ApiObject(name = "Privacy", group = "User", stage = ApiStage.RC)
public class Privacy extends BaseModel{

	@JsonIgnore
	@QueryType(PropertyType.NONE)
	private static final long serialVersionUID = 1140295608373327662L;
	
	@ApiObjectField(description = "subscriber ", order = 10)
	@NotNull
	private User subscriber;
	
	@ApiObjectField(description = "video", order = 20)
	@NotNull
	private Video video;

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
