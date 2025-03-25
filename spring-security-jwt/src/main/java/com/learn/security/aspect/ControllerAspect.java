package com.learn.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Before("execution (* com.learn.security.controller.* .*(..))")
    public void executeBeforeMethodCall() {
        logger.info("Logging before controller method call.");
    }

    @AfterReturning(pointcut = "execution (* com.learn.security.controller.*.*(..))", returning = "result")
    public void executeAfterMethodCallOnlyIfCompletedSuccessfully(Object result) {
        logger.info("Log returned value: {}", result.toString());
        logger.info("Logging after returning controller method call as it completed successfully.");
    }

    @Around("execution (* com.learn.security.controller.*.*(..))")
    public Object aroundMethodControllerCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String controllerMethodName = proceedingJoinPoint.getSignature().getName();
        String shortString = proceedingJoinPoint.getSignature().toShortString();
        Object[] args = proceedingJoinPoint.getArgs();
        String argsString = Arrays.toString(args);
        logger.info("Logging around controller method call.");
        logger.info("----- {}", controllerMethodName);
        logger.info("----- Args: {}", argsString);
        long startTime = System.currentTimeMillis();
        logger.info("Log start time: {}", startTime);
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis() - startTime;
        logger.info("Log time required by controller: {}", endTime);
        return result;
    }

    @After("execution (* com.learn.security.controller.*.*(..))")
    public void executeAfterMethodCallIndependentOfOutcome() {
        logger.info("Log after controller method call.");
    }
}
