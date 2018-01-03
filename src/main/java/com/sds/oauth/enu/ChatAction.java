package com.sds.oauth.enu;

public enum ChatAction {
	
	CREATE(0),
	DELETE(1),
	CHANGE(2),
	JOIN(3),
	EXIT(4);

	
	private final int value;
	
	private ChatAction(int value){
		this.value = value;
	}
	
	public int getValue() {
	    return value;
	}
}
