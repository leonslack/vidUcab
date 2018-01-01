package com.ucab.restful.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

@Entity
@Table(name = "[User]")
@ApiObject(name = "User", group = "User", stage = ApiStage.RC)
public class User  extends BaseModel{

	@QueryType(PropertyType.NONE)
	@JsonIgnore
	private static final long serialVersionUID = 4382596676123064009L;
	
	@ApiObjectField(description = "nickname of User (unique)", order = 10,required = true)
	private String nickname;

	@ApiObjectField(description = "First Name of User", order = 10,required = true)
	private String firstName;

	@ApiObjectField(description = "Last Name of User", order = 20,required = true)
	private String lastName;

	@ApiObjectField(description = "clientid for google auth", order = 30)
	private String clientid;
	
	@ApiObjectField(description = "clientSecret for google auth", order = 35)
	private String clientSecret;
	
	@ApiObjectField(description = "Set of videos", order = 40)
	private Set<Video> videos = new HashSet<>();

	@Column(name = "nickname",unique=true)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@OneToMany(fetch = FetchType.EAGER,mappedBy="owner",cascade = CascadeType.ALL)
	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	@Column(name = "clientid")
	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	@Column(name = "clientsecret")
	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Column(name = "firstname", length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	@Column(name = "lastname", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}



	@Override
	public String toString() {
		return "User : [" + (firstName != null ? "first name=" + firstName + ", \n" : "")
		+ (lastName != null ? "last Name=" + lastName + ", \n" : "")
		+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}


}
