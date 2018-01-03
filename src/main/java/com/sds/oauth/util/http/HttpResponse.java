package com.sds.oauth.util.http;

public class HttpResponse {
	
	private int responseCode;
	
	private String responseData;
	
	public void setResponseCode(int code){
		this.responseCode = code;
	}

	public int getResponseCode(){
		return this.responseCode;
	}
	
	public void setResponseData(String data){
		this.responseData = data;
	}

	public String getResponseData(){
		return responseData;
	}
}
