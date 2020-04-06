package com.komsia.kom.domain;

import lombok.Data;

@Data
public class ReplyVO {

	private int replyNo;
	private int boardNo;
	private String boardType;
	private String boardSubType;
	private String replyContent;
	private String replyRegId;
	private String replyRegDttm;
	private String modId;
	private String modDttm;
	
}
