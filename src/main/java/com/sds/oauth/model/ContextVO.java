package com.sds.oauth.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContextVO {
	private static ContextVO 					instance;
	
	private static Object 						instanceObject= new Object();
	
	// Daemon Managmen	
	private boolean 							isDaemonError;
	private boolean 							isDaemonStart;
	
	private String								hostContextPath;
	private int    								hostPort;	
	
	private String								proxyIp;
	private int    								proxyPort;	
	
	private Map<String, String> 				commandLineArgsMap;

		
	public ContextVO(){
		commandLineArgsMap = new HashMap<String, String>();
		
		isDaemonStart = false;
		isDaemonError = false;
	}
	
	public static ContextVO getInstance(){
		synchronized(instanceObject){
			if(instance == null)
			instance = new ContextVO();			
		}
		
		return instance;
	}

	public boolean getIsDaemonStart(){
		return this.isDaemonStart;
	}
	
	public void setDaemonStart(boolean state){
		this.isDaemonStart = state;
	}
	
	public boolean getIsDaemonError(){
		return this.isDaemonError;
	}
	
	public void setIsDaemonError(boolean state){
		this.isDaemonError = state;
	}

	public void setHostContextPath(String contextPath) {
		this.hostContextPath = contextPath;
	}
	
	public String getHostContextPath() {
		return hostContextPath;
	}
	
	public void setHostPort(int port) {
		this.hostPort = port;
	}
	
	public int getHostPort() {
		return hostPort;
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public Map<String, String> getCommandLineArgsMap(){
		return this.commandLineArgsMap;
	}

	public void setDaemonError(boolean isDaemonError) {
		this.isDaemonError = isDaemonError;
	}

	public void setCommandLineArgsMap(Map<String, String> argsMap){
		this.commandLineArgsMap = argsMap;
	}
}