package com.ucab.restful.data.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.pojo.ApiStage;

@Entity
@Table(name = "[Privacy]")
@ApiObject(name = "Privacy", group = "User", stage = ApiStage.RC)
public class Privacy extends BaseModel{

	private static final long serialVersionUID = 1140295608373327662L;

}
