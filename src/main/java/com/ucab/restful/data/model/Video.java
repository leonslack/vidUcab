package com.ucab.restful.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

@Entity
@Table(name = "[Video]")
@ApiObject(name = "Video", group = "User", stage = ApiStage.RC)
public class Video extends BaseModel{

	@JsonIgnore
	@QueryType(PropertyType.NONE)
	private static final long serialVersionUID = -1863217112101585347L;
	
	@ApiObjectField(description = "owner of video", order = 10)
	private User owner;
	
	@ApiObjectField(description = "tittle of video", order = 20)
	private String tittle;

	@ApiObjectField(description = "description of video", order = 30)
	private String description;
	
	@ApiObjectField(description = "link of video", order = 40)
	private String link;
	
	@Column(name = "link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "tittle")
	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
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
