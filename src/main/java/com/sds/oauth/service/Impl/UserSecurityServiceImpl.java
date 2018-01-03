package com.sds.oauth.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.sds.oauth.dao.UserSecurityDao;
import com.sds.oauth.model.ContextVO;
import com.sds.oauth.model.JsonReturnVO;
import com.sds.oauth.model.UserAuthorityVO;
import com.sds.oauth.model.UserDetail;
import com.sds.oauth.model.UserVO;
import com.sds.oauth.service.UserSecurityService;
import com.sds.oauth.util.JsonUtil;
import com.sds.oauth.util.ObjectUtil;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserSecurityServiceImpl.class);
	
	@Autowired
	private UserSecurityDao securityDao;
	
	@Override 
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		UserVO userVO = new UserVO();
		userVO.setName(name);
		
		return readUser(userVO); 
	} 
	
	@Override 
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
		
    public UserDetail readUser(UserVO userVO){
    	
		Map<String, Object> param = ObjectUtil.ConverObjectToMap(userVO);
		
		Map<String, Object> userMap = securityDao.readUser(param); 
				
		UserDetail userDetail = new UserDetail();
		
		if(userMap != null) {
			userDetail.setUsername((String)userMap.get("name"));
			userDetail.setPassword((String)userMap.get("pwd"));
			userDetail.setAccountNonExpired(((Integer)userMap.get("isAccountNotExpired"))>0?true:false);
			userDetail.setAccountNonLocked(((Integer)userMap.get("isAccountNonLocked"))>0?true:false);
			userDetail.setCredentialsNonExpired(((Integer)userMap.get("isCredentialsNonExpired"))>0?true:false);		
			userDetail.setEnabled(((Integer)userMap.get("isEnabled"))>0?true:false);
			userDetail.setAuthorities(getAuthorities((Long)userMap.get("id")));
		}
				
    	return userDetail; 
    }
    
    public void createUser(UserVO userVO) {
    	String rawPassword = userVO.getPwd();
    	String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword); 
    	
		Map<String, Object> param = ObjectUtil.ConverObjectToMap(userVO);
		
		param.put("pwd", encodedPassword);
		
    	securityDao.createUser(param);    
    	
    	List<UserAuthorityVO> authorities = new ArrayList<UserAuthorityVO>();
    	
    	if(userVO.getAutorities() == null) {
    		UserAuthorityVO authority = new UserAuthorityVO();
    		
    		authority.setId((Long)param.get("id"));
    		authority.setAuthority("USER");
    		authorities.add(authority);
    	}else {
    		
    		for(int index=0; index < userVO.getAutorities().size(); index++) {
        		UserAuthorityVO authority = new UserAuthorityVO();
        		
        		authority.setId((Long)param.get("id"));
        		authority.setAuthority(userVO.getAutorities().get(index));
        		authorities.add(authority);
    		}
    	}
    	
    	param.put("list", authorities);    	
    	securityDao.createAuthorities(param);
    }
    
    public void deleteUsers(List<UserVO> userList) {
    	    	
    	List<Long> idList = new ArrayList<Long>();
    	  		
    	for(int index=0; index < userList.size(); index++) {

        	idList.add(userList.get(index).getId());
    	}
    	
    	Map<String, Object> param = new HashMap<String, Object>();
    	
    	param.put("list", idList);
    	
    	securityDao.deleteUsers(param);
    }
    
	public Collection<GrantedAuthority> getAuthorities(Long id) { 
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("id", id);	
		
		List<Map<String, Object>> authorityListMap = securityDao.readAuthorities(param); 
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
		
		for(int i=0; i<authorityListMap.size(); i++) {
			String authority = (String)authorityListMap.get(i).get("authority");
			authorities.add(new SimpleGrantedAuthority(authority)); 
		}
		
		return authorities; 
	}
}
