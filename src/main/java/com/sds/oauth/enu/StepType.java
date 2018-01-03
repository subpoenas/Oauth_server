package com.sds.oauth.enu;

public enum StepType {
	FLOW("flow"),
	SET("set"),
	DISPLAY("display");
	
	private String type;
	
	StepType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
