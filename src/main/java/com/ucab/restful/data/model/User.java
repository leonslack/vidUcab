package com.ucab.restful.data.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;

@Entity
@Table(name = "[User]")
@Where(clause ="is_active = true")
@ApiObject(name = "User", group = "User", stage = ApiStage.RC)
public class User  implements Serializable{

	private static final long serialVersionUID = 8034417743304499377L;

	
	@ApiObjectField(description = "ID of the record", order = 1000,required = false)
	private UUID id;

	
	@ApiObjectField(description = "First Name of User", order = 10,required = true)
	private String firstName;

	
	@ApiObjectField(description = "Last Name of User", order = 20,required = true)
	private String lastName;

	
	@ApiObjectField(description = "ID of the record", order = 30)
	private String driveToken;
	
	@ApiObjectField(description = "Indicates if the record is active or not", order = 1001)
	private boolean isActive = true;

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

	@Column(name = "is_active", nullable = false)
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "drivetoken")
	public String getDriveToken() {
		return this.driveToken;
	}

	public void setDriveToken(String driveToken) {
		this.driveToken = driveToken;
	}


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Id: ").append(this.id).append(", firstName: ").append(this.firstName).append(", lastName: ")
				.append(this.lastName).append(", Token: ").append(this.driveToken);
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (id == null || obj == null || getClass() != obj.getClass())
			return false;
		User toCompare = (User) obj;
		return id.equals(toCompare.id);
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

}
