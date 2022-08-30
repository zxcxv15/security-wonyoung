package com.study.security_wonyoung.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class LogAop {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAop.class);
	
	@Pointcut("@annotation(com.study.security_junil.handler.aop.annotation.Log)")
	private void enableLog() {}
	
	@Around("enableLog()")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		Map<String, Object> params = getParams(joinPoint);
		LOGGER.info(">>>>> Method call >>>>> {}({})", 
				joinPoint.getSignature().getName(),
				params);
		
		Object result = joinPoint.proceed();
		
		LOGGER.info(">>>>> Method End >>>>> {} --return--> {})", 
				joinPoint.getSignature().getName(),
				result);
		return result;
	}
	
	private Map<String, Object> getParams(ProceedingJoinPoint joinPoint) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		String[] argNames = codeSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();
		
		for(int i = 0; i < argNames.length; i++) {
			params.put(argNames[i], args[i]);
		}
		
		return params;
	}
}


