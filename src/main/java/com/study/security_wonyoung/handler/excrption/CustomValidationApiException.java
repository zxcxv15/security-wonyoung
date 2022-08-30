package com.study.security_wonyoung.handler.excrption;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class CustomValidationApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap; // 에러메세지를 담으려고
	
	
	// 아무것도 없는 
	public CustomValidationApiException() {
		this("error", new HashMap<String, String>()); // 자기자신 생성자 호출
	}
	
	// 메세지 받는 
	public CustomValidationApiException(String message) {
		this(message, new HashMap<String, String>());	
	}
	
	// 메세지와 Map을 받는
	public CustomValidationApiException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}

}
