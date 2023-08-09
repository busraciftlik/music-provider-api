package com.atmosware.busraciftlik.music.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.atmosware.busraciftlik.music.provider..*(..)) && !execution(* com.atmosware.busraciftlik.music.provider.config.filter..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        final String className = joinPoint.getTarget().getClass().getSimpleName();
        final String methodName = joinPoint.getSignature().getName();
        log.debug("Before method execution {}#{}", className, methodName);
        final Object result = joinPoint.proceed();
        log.debug("After method execution {}#{}", className, methodName);
        return result;
    }

    @Before("execution(* com.atmosware.busraciftlik.music.provider.controller..*(..))")
    public void logControllerMethodParams(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        log.info("Before endpoint method execution {}#{} | args:{}", className, methodName, StringUtils.joinWith(";", methodArgs));
    }
}