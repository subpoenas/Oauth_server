package com.sds.oauth.util;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class RuntimeUtiil {

	public String loadClass(String jarpath, String classpath) throws Exception{
		
		URL[] classLoaderUrls = new URL[]{new URL(jarpath)};
		
		URLClassLoader child = new URLClassLoader (classLoaderUrls, this.getClass().getClassLoader());
		Class classToLoad = Class.forName (classpath, true, child);				
		
		Method method = classToLoad.getDeclaredMethod ("execute", String[].class);
		Object instance = classToLoad.newInstance ();
		Object result = method.invoke (instance);
		
		return result.toString();
	}
}
