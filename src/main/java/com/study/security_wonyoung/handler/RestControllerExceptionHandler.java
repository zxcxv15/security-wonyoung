package com.study.security_wonyoung.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.study.security_wonyoung.handler.excrption.CustomValidationApiException;
import com.study.security_wonyoung.wep.dto.CMRespDto;

@RestController
@ControllerAdvice
public class RestControllerExceptionHandler {
															//.class: 해당 클래스 타입을 말한다.
	@ExceptionHandler(CustomValidationApiException.class) 	//CustomValidationApiException에 만들어 놓은 예외를 받겠다.
	public ResponseEntity<?> validationApException(CustomValidationApiException e){
		return ResponseEntity.badRequest().body(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()));
	}
}
