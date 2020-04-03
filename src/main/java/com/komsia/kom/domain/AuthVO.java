package com.komsia.kom.domain;

import lombok.Data;

@Data
public class AuthVO {
	
	private Integer roleId;
	private String roleName;
	private String roleDef;
	
	private Long userNO;
	private String userId;
	private String userName;
	private String tel;
	private String email;
	
}
