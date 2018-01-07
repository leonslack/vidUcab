package com.ucab.restful.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	public String getTittle() {
		return title;
	}

	public void setTittle(String tittle) {
		this.title = tittle;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	

}
