package com.sds.oauth.enu;

public enum LogicalDataType {
	CONTENT("CONTENT"),
	TEXT("TEXT"),
	BOOLEAN("BOOLEAN"),
	NUMBER("NUMBER"),
	BIGNUMER("BIGNUMER"),	
	TITLE("TITLE"),
	DESC("DESC"),
	DATETIME("DATETIME"),
	CHECKBOX("CHECKBOX"),
	PICKLIST("PICKLIST"),
	DPICKLIST("DPICKLIST"),
	LOOKUP("LOOKUP"),
	MASTERDETAIL("MASTERDETAIL");	
	private String type;
	
	LogicalDataType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
