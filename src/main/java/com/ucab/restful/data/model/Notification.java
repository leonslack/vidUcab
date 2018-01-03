package com.ucab.restful.data.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.pojo.ApiStage;

@Entity
@Table(name = "[Notification]")
@ApiObject(name = "Notification", group = "User", stage = ApiStage.RC)
public class Notification extends BaseModel{

	private static final long serialVersionUID = 2146139360684639245L;

}
