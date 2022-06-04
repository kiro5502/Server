package com.ajou.mse.magicaduel.server.aspect;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.ajou.mse.magicaduel.server.controller..*.*(..))")
    public Object logApi(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String requestId = UUID.randomUUID().toString();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        long start = System.currentTimeMillis();
        logger.info("[{}] Request: {} {}: {}", requestId, request.getMethod(), request.getRequestURL(),
                proceedingJoinPoint.getArgs()[0].toString());

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
        logger.info("[{}] Response: {} {}: {} ({}ms)", requestId, request.getMethod(), request.getRequestURL(),
                result.toString(), end - start);

        return result;
    }
}
