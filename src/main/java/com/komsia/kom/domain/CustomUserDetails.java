package com.komsia.kom.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User{
	
	private UserVO user;
	private List<String> roles;
	private int userNo;
	private String userName;
	
	
	public CustomUserDetails(UserVO user, List<String> roles) {
		super(user.getUserId(), user.getPassword(), true, true, true, true, 
				roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

	public UserVO getUser() {
		return user;
	}


	public void setUser(UserVO user) {
		this.user = user;
	}


	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
}