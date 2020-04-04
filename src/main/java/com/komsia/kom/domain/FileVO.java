package com.komsia.kom.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileVO {
	
	private int fileNo;
	private int boardNo;
	private int fileSeq;
	private String boardType;	
	private String boardSubType;
	private String fileNm;
	private String fileExt;
	private String fileDir;
	private String useYn;
	
	private String regId;
	private String regDttm;
	private String modId;
	private String modDttm;

	private MultipartFile file;
}
