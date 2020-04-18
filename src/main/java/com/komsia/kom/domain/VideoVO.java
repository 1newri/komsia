package com.komsia.kom.domain;

import lombok.Data;

@Data
public class VideoVO {

	private int videoNo;
	private String boardType;
	private String boardSubType;
	private String videoUrl;
	private String thumbnailUrl;
	private String regId;
	private String regDttm;
	private String modId;
	private String modeDttm;
}
