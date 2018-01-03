package com.sds.oauth.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxyConfig {

	private String httpProxyHost;
	
	private int	   httpProxyPort;
	
	
	public void setHttpProxyHost(String httpProxyHost){
		this.httpProxyHost = httpProxyHost;
	}
	
	public String getHttpProxyHost(){
		return httpProxyHost;
	}

	public void setHttpProxyPort(int httpProxyPort){
		this.httpProxyPort = httpProxyPort;
	}
	
	public int getHttpProxyPort(){
		return httpProxyPort;
	}

}
