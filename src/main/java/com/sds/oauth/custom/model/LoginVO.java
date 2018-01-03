package com.sds.oauth.custom.model;

import javax.validation.constraints.NotNull;

public class LoginVO {

	@NotNull
	String		  id;
	@NotNull
	String		  pwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
