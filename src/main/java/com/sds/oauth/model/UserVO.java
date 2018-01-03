package com.sds.oauth.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserVO {
	
	@NotNull
	Long   id;
	
	@NotNull
	String name;
	
	@NotNull
	String pwd;
	
	List<String> autorities;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List<String> getAutorities() {
		return autorities;
	}

	public void setAutorities(List<String> autorities) {
		this.autorities = autorities;
	}
}
