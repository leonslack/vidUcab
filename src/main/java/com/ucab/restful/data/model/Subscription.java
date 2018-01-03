package com.ucab.restful.data.model;

import javax.persistence.CascadeType;
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
@Table(name = "[Subscription]")
@ApiObject(name = "Subscription", group = "User", stage = ApiStage.RC)
public class Subscription extends BaseModel{
	
	@JsonIgnore
	@QueryType(PropertyType.NONE)
	private static final long serialVersionUID = -8126017103837010896L;
	
	@ApiObjectField(description = "owner of channel", order = 10)
	@NotNull
	private User owner;
	
	@ApiObjectField(description = "Subscriber of channel", order = 10)
	@NotNull
	private User subscriber;

	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="subscriber_id", nullable = false)
	public User getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(User subscriber) {
		this.subscriber = subscriber;
	}
	
	

}
