package com.komsia.kom.domain;

import lombok.Data;

@Data
public class ActivityVO {
	
	private int boardNo;
	private String boardType;
	private String boardSubType;
	
	private String title;
	private String content;
	
	private int hit;
	private String useYn;
	
	private String regId;
	private String regDttm;
	private String modId;
	private String modeDttm;

}
