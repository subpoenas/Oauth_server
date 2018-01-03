package com.sds.oauth.model;

import org.json.simple.JSONObject;

public class JsonReturnVO {	
	JSONObject dataObject = new JSONObject();
	JSONObject errorObject = new JSONObject();

	public JsonReturnVO() {
		
		errorObject.put("message", "ok");
		errorObject.put("error", false);	
		
		dataObject.put("state", errorObject);
	}
	
	public void setData(String key, Object value) {
		dataObject.put(key, value);	
	}
	
	public void setErrorMessage(String message) {
		errorObject.put("message",  message);
		errorObject.put("error", true);
		
		dataObject.put("state", errorObject);
	}
	
	public String toString() {
		return this.dataObject.toString();
	}
}
