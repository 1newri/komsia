package com.komsia.kom.domain;

import lombok.Data;

@Data
public class AuthVO {
	
	private int roleId;
	private String roleIds;
	private String roleName;
	private String roleDef;
	
	private Long userNo;
	private String userId;
	private String userName;
	private String tel;
	private String email;
	
}
