package com.sds.oauth.dao.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.sds.oauth.dao.UserSecurityDao;
import com.sds.oauth.util.MyBatisUtil;

@Repository
public class UserSecurityDaoImpl implements UserSecurityDao {
	
	@Value("${dao.prefix.security}")
    private String prefix;
	 
	@Autowired
	private MyBatisUtil myBatisUtil;
	
	public Map<String, Object>		 				readUser(Map<String, Object> param){		
		String sqlId = "getUser";
		return myBatisUtil.selectOne(prefix, sqlId, param);		
	}
	
	public void										createUser(Map<String, Object> param){
		String sqlId = "createUser";
		myBatisUtil.insert(prefix, sqlId, param);	
	}
	
	public void										deleteUsers(Map<String, Object> param){
		String sqlId = "deleteUsers";
		myBatisUtil.delete(prefix, sqlId, param);	
	}	
	
	public List<Map<String, Object>> 				readAuthorities(Map<String, Object> param){
		String sqlId = "getAuthorities";
		return myBatisUtil.selectList(prefix, sqlId, param);	
	}
	
	public void										createAuthorities(Map<String, Object> param){
		String sqlId = "createAuthorities";
		myBatisUtil.insert(prefix, sqlId, param);	
	}	
}