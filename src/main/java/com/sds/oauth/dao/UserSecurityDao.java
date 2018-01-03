package com.sds.oauth.dao;

import java.util.List;
import java.util.Map;


public interface UserSecurityDao {
		
	public Map<String, Object>				readUser(Map<String, Object> param);
	
	public void								createUser(Map<String, Object> param);
	
	public void								deleteUsers(Map<String, Object> param);
	
	public List<Map<String, Object>> 		readAuthorities(Map<String, Object> param);
	
	public void								createAuthorities(Map<String, Object> param);
}
	