package com.cognizant.project.elearning.loggers;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
 
@Aspect
@Component
public class ErrorLog {
 
    private static final Logger logger = LoggerFactory.getLogger(ErrorLog.class);
 
    @AfterThrowing(pointcut = "execution(* com.cognizant.project.elearning.controller.*.*(..))",
            throwing = "ex")
    public void logError(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.error("Exception in {}.{}() with message = '{}', cause = '{}'",
                className, methodName, ex.getMessage(), ex.getCause() != null
                        ? ex.getCause() : "NULL");
    }
}
 
 
 