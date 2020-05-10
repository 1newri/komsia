package com.komsia.kom.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ActivityVO {
	
	private int boardNo;
	private int boardOrder;
	private String boardDate;
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
	
	private String crud;

	private MultipartFile file;
	
	private int fileNo;
	private int fileSeq;
	private String fileNm;
	private String fileDir;

}
