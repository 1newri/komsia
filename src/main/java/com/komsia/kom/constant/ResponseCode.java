package com.komsia.kom.constant;

public class ResponseCode {

	public static final String RESPONSE_OK = "0000";
	public static final String RESPONSE_OK_MSG = "성공";

	public static final String RESPONSE_FAIL = "9999";
	public static final String RESPONSE_FAIL_MSG = "실패";
	
	public static final String WRITER_AUTH_ERROR = "1000";
	public static final String WRITER_AUTH_ERROR_MSG = "작성자만 수정 또는 삭제가 가능합니다.";
	
	public static final String DUPLICATE_USER = "duplicate";
	public static final String DUPLICATE_USER_MSG = "이미 사용중인 ID 입니다.";
	
	public static final String INVALID_PASSWORD = "invalid";
	public static final String INVALID_PASSWORD_MSG = "현재 비밀번호가 일치하지 않습니다.";
	
	public static final String NOT_PROCEED_LENGTH = "length";
	public static final String NOT_PROCEED_LENGTH_MSG = "동영상은 최대 5개까지 등록가능합니다.";
	
}
