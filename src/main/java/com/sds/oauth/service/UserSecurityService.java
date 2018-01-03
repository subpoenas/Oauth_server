package com.sds.oauth.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sds.oauth.model.UserDetail;
import com.sds.oauth.model.UserVO;


public interface UserSecurityService extends UserDetailsService {
	
    public Collection<GrantedAuthority> getAuthorities(Long id);
    
    public UserDetail readUser(UserVO user); 
    
    public void createUser(UserVO user); 
    
    public void deleteUsers(List<UserVO> userList);
    	
    public PasswordEncoder passwordEncoder();
    
}