package com.learn.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    @Before("execution (* com.learn.security.service.*.*(..))")
    public void logBeforeServiceMethodCall() {
        logger.info("Log before service method call.");
    }

    @After("execution (* com.learn.security.service.*.*(..))")
    public void afterServiceMethodCall() {
        logger.info("Log after service method call.");
    }

    @AfterReturning(pointcut = "execution (* com.learn.security.service.*.*(..))", returning = "result")
    public void afterReturnOfServiceMethodCall(Object result) {
        logger.info("Log service result value: {}", result.toString());
    }

    @AfterThrowing(pointcut = "execution (* com.learn.security.service.*.*(..))", throwing = "exception")
    public void afterExceptionThrowOfServiceMethodCall(Exception exception) {
        logger.info("Log exception message: {}", exception.getMessage());
    }

    @Around("execution (* com.learn.security.service.*.*(..))")
    public Object afterAroundOfServiceMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Around of service method start: ");
        Object proceed = proceedingJoinPoint.proceed();
        logger.info("Around of service method ends: ");
        return proceed;
    }
}
