
package com.komsia.kom.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Resource {
	
	private Integer id;
	private String name;
	private Set<RoleResource> roleResource = new HashSet<RoleResource>();
}
