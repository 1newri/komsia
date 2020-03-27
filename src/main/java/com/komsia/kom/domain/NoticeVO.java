package com.komsia.kom.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class NoticeVO {
	
	private int boardNo;
	private String boardType;
	
	private int fileNo;
	private String title;
	private String content;
	
	private int hit;
	private String useYn;
	
	private String regId;
	private String regDttm;
	private String modId;
	private String modeDttm;

	private String prevNoticeNo;
	private String prevNoticeTitle;
	
	private String nextNoticeNo;
	private String nextNoticeTitle;
	
	@JsonIgnore
	private int start;
	@JsonIgnore
	private int length;
	@JsonIgnore
	private int draw;
}
