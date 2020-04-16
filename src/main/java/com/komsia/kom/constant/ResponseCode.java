package com.komsia.kom.constant;

public class ResponseCode {

	public static final String RESPONSE_OK = "0000";
	public static final String RESPONSE_OK_MSG = "성공";

	public static final String RESPONSE_FAIL = "9999";
	public static final String RESPONSE_FAIL_MSG = "실패";
	
	public static final Object DUPLICATE_USER = "duplicate";
	public static final Object DUPLICATE_USER_MSG = "이미 사용중인 ID 입니다.";
	
	public static final Object INVALID_PASSWORD = "invalid";
	public static final Object INVALID_PASSWORD_MSG = "현재 비밀번호가 일치하지 않습니다.";
	
}
