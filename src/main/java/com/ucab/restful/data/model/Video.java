package com.ucab.restful.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import com.ucab.restful.commons.enums.PrivacyType;

@Entity
@Table(name = "[Video]")
@ApiObject(name = "Video", group = "User", stage = ApiStage.RC)
public class Video extends BaseModel{

	@JsonIgnore
	@QueryType(PropertyType.NONE)
	private static final long serialVersionUID = -1863217112101585347L;
	
	@ApiObjectField(description = "owner of video", order = 10)
	@NotNull
	private User owner;
	
	@ApiObjectField(description = "title of video", order = 20)
	private String title;

	@ApiObjectField(description = "description of video", order = 30)
	private String description;
	
	@ApiObjectField(description = "link of video", order = 40)
	@NotNull
	private String link;
	
	@ApiObjectField(description = "type of privacy", order = 50)
	private PrivacyType privacyType;
	
	@ApiObjectField(description = "id of video in google", order = 60)
	private String googleId;
	
	private Set<Privacy> privacy = new HashSet<>();
	
	@ApiObjectField(description = "Duration of video", order = 70)
	@NotNull
	private Integer duration;
	
	
	@Column(name = "duration")
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<Privacy> getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Set<Privacy> privacy) {
		this.privacy = privacy;
	}

	@Column(name = "google_id")
	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	@Column(name = "privacy_type")
	@Enumerated(EnumType.STRING)
	public PrivacyType getPrivacyType() {
		return privacyType;
	}

	public void setPrivacyType(PrivacyType privacyType) {
		this.privacyType = privacyType;
	}

	@Column(name = "link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String tittle) {
		this.title = tittle;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	

}
