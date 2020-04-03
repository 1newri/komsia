package com.komsia.kom.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Role {
	
	private Integer roleId;
	private String roleName;
	private String roleDef;
	
	private Set<Authorities> authorities = new HashSet<Authorities>();
	private Set<RoleResource> roleResource = new HashSet<RoleResource>();
	
}
