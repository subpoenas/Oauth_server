package com.sds.oauth.util.http;


public enum HttpApplicationType {
	FORM("application/x-www-form-urlencoded"),
	JSON("application/json");
	
	private String type;
	
	private HttpApplicationType(String type){		
		this.type = type;		
	}
	
	public String getType(){
		return type;
	}
}