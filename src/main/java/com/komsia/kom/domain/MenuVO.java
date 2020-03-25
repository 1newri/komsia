package com.komsia.kom.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MenuVO {
	
	private int menuId;
	private int level;
	private String url;
	private int pid;
	private String name;
	private String type;
	private String description;
	private int ordering;
	private String useYn;
	private String regId;
	private String regDttm;
	private String modId;
	private String modDttm;
	
	@JsonIgnore
	private int start;
	private int length;
	private int draw;
}
