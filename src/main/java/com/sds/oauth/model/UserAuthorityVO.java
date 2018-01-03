package com.sds.oauth.model;

/**
 * 
 * @author SDS
 *	- User 생성 시 권한 값 설정 VO 
 *
 */

public class UserAuthorityVO {
	
	Long   id;
	
	String authority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
