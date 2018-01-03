package com.sds.oauth.enu;

public enum ProcessType {
	EVENT("E"),
	TASK("T"),
	APPROVE("A");
	
	private String type;
	
	ProcessType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
