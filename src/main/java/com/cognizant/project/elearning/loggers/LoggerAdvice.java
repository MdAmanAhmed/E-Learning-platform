package com.cognizant.project.elearning.loggers;
import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;



@Aspect
@Component
@Slf4j
public class LoggerAdvice {
 
	@Pointcut(value="execution(* com.cognizant.project.elearning.controller.*.*(..))")
	public void myPointcut() {
	}
	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
		log.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
				+ mapper.writeValueAsString(array));
		Object object = pjp.proceed();
		log.info(className + " : " + methodName + "()" + "Response : "
				+ mapper.writeValueAsString(object));
		return object;
	}
 
}