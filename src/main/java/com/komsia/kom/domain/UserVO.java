package com.komsia.kom.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
public class UserVO {
	
	private Long userNo;
	private String userId;
	private String password;
	private String userName;
	private String tel;
	private String sex;
	private String email;
	private String birth;
	private String zip;
	private String addr;
	private String addrDetail;
	
	private String regId;
	private String regDttm;
	private String modId;
	private String modDttm;
	
	private List<Role> roles = new ArrayList<Role>();
	
	@JsonIgnore
	private int start;
	@JsonIgnore
	private int length;
	@JsonIgnore
	private int draw;
	
}
