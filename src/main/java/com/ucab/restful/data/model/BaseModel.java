package com.ucab.restful.data.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

@MappedSuperclass
@ApiObject
@Where(clause ="is_active = true")
public abstract class BaseModel implements Serializable{

	@QueryType(PropertyType.NONE)
	@JsonIgnore
	private static final long serialVersionUID = -5488708916335056758L;
	
	@ApiObjectField(description = "ID of the record", order = 1000,required = false)
	private UUID id;
	
	@ApiObjectField(description = "Indicates if the record is active or not", order = 1001)
	private boolean isActive = true;
	
	@ApiObjectField(description = "When the record was created", order = 1004)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime createdAt;
	
	public BaseModel() {

	}
	
	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BaseModel)) {
			return false;
		}
		BaseModel other = (BaseModel) obj;
		return getId().equals(other.getId());
	}
	
	@Id
	@Type(type = "pg-uuid")
	@GeneratedValue(generator = "uuid-gen",strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@Column(name = "ID", unique = true, nullable = false)
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	@Column(name = "is_active", nullable = false)
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	@PrePersist
	void onCreate() {
		LocalDateTime currentDate = LocalDateTime.now();
		setCreatedAt(currentDate);
	}
	
	@Override
	public String toString() {
		return " [id=" + id + ", \nisActive=" + isActive
				+ ", \ncreatedAt=" + createdAt+"]";
	}

}
