package com.komsia.kom.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User{
	
	private Long userNo;
	private String userName;

	public CustomUserDetails(String userId, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(userId, password, true, true, true, true, authorities);
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserId() {
		return super.getUsername();
	}
}
