package com.komsia.kom.domain;

import lombok.Data;

@Data
public class FileVO {
	
	private int fileNo;
	private int boardNo;
	private int fileSeq;
	private String boardType;
	private String fileNm;
	private String fileExt;
	private String useYn;
	
	private String regId;
	private String regDttm;
	private String modId;
	private String modDttm;
	
}
