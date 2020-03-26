package com.komsia.kom.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BoardVO {
	
	private int boardNo;
	private String boardType;
	
	private int fileNo;
	private String title;
	private String contents;
	
	private int hit;
	private String useYn;
	
	private String regId;
	private String regDttm;
	private String modId;
	private String modeDttm;
	
	@JsonIgnore
	private int start;
	@JsonIgnore
	private int length;
	@JsonIgnore
	private int draw;
}
