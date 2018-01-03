package com.sds.oauth.util.http;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sds.oauth.model.ContextVO;


public class HttpSendManager {

	private static final Logger logger = LoggerFactory.getLogger(HttpSendManager.class);
	
	static int inc = 0;

	// HTTP POST request
	public HttpResponse sendData(String method, String url, String authorization, String body, HttpApplicationType type, int timeout){
		
		HttpResponse response = new HttpResponse();
		
		try{
		
			URL obj = new URL(url);
			
			HttpURLConnection con = null;
			Proxy proxy = null;  
			
			HttpProxyConfig httpConfig = null;
		
			ContextVO  _systemContext = ContextVO.getInstance();
			
			if(!_systemContext.getProxyIp().isEmpty()) {				
				httpConfig = new HttpProxyConfig();
				httpConfig.setHttpProxyHost(_systemContext.getProxyIp());
				httpConfig.setHttpProxyPort(_systemContext.getProxyPort());
			}else {

			}
			
			if(httpConfig != null){
//				logger.info("Proxy true");
				proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpConfig.getHttpProxyHost(), httpConfig.getHttpProxyPort())); 
				con =  (HttpURLConnection) obj.openConnection(proxy);
			}else{
//				logger.info("Proxy false");
				con =  (HttpURLConnection) obj.openConnection();
			}		

			//add reuqest header
			con.setRequestMethod(method);
			con.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.5");
			con.setRequestProperty("Content-Type", type.getType());
			
			if(authorization != null)
				con.setRequestProperty("Authorization", authorization);
			con.setConnectTimeout(timeout);
			
			// Send post request
			con.setDoInput(true); 
			con.setDoOutput(true); 
			
			if(method.compareTo("POST") == 0){
				
				String extbody = body;
				
				byte ptext[] = extbody.getBytes("UTF-8");
				String sendBody = new String(ptext);
				
	//			logger.debug("\nSending 'POST' request to URL : " + url);
	//			logger.debug("contentType : " + type.getType());
	//			logger.debug("body : " + sendBody);
	
				BufferedOutputStream  wr = new BufferedOutputStream (con.getOutputStream());
				wr.write(sendBody.getBytes("UTF-8"));
				wr.flush();
				wr.close();
			}else{
	//			logger.debug("\nSending 'GET' request to URL : " + url);
	//			logger.debug("contentType : " + type.getType());
			}
			
			int responseCode = con.getResponseCode();
		
			BufferedReader in = null;			
			
			if(responseCode == 200){
				in = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
			}else{				
//				logger.info("con.getInputStream() "+con.getInputStream());
//				if(con.getInputStream() != null){
//				in = new BufferedReader(
//						new InputStreamReader(con.getInputStream()));
//				}
				
				if(con.getErrorStream() != null){
				in = new BufferedReader(
						new InputStreamReader(con.getErrorStream()));	
				}			
			}
			
			String inputLine;
			StringBuffer responseData = new StringBuffer();
	 
			if(in != null){
				while ((inputLine = in.readLine()) != null) {
					responseData.append(inputLine);
				}
				
				in.close();
			}
			
			response.setResponseCode(responseCode);
			response.setResponseData(responseData.toString());
			
		}catch(MalformedURLException me){
			logger.error(me.getMessage());
			response.setResponseCode(-1);
			response.setResponseData("MalformedError");
			
		}catch(IOException ie){
			logger.error(ie.getMessage() + " with "+url);
			response.setResponseCode(-2);
			response.setResponseData("ConnectionError");
		}		
		
		return response;
	}
}
