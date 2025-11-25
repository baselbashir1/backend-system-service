package com.task.backendsystem.aop;

import com.task.backendsystem.model.Log;
import com.task.backendsystem.repository.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

@Slf4j
@Aspect
@Configuration
@RequiredArgsConstructor
public class Logging {

    private final LogRepository logRepository;

    @Around("execution(* com.task.backendsystem.controller..*.*(..))")
    public Object logApiMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String correlationId = request.getHeader("X-Correlation-ID");

        try {
            Object result = joinPoint.proceed();
            saveLog(request, correlationId, "API call successful", null);
            return result;
        } catch (Throwable e) {
            saveLog(request, correlationId, e.getMessage(), e);
            throw e;
        }
    }

    private void saveLog(HttpServletRequest request, String correlationId, String message, Throwable throwable) {
        Log logEntry = new Log();
        logEntry.setEndpoint(request.getServletPath());
        logEntry.setIp(getIpAddress(request));
        logEntry.setMessage(message);
        logEntry.setCorrelationId(correlationId);

        if (throwable != null) {
            log.error("Exception at endpoint {}: {}", request.getServletPath(), throwable.getMessage(), throwable);
        } else {
            log.info("Success at endpoint {}: {}", request.getServletPath(), message);
        }

        logRepository.save(logEntry);
    }

    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }

        if ("127.0.0.1".equals(ipAddress) || "localhost".equalsIgnoreCase(ipAddress)) {
            try {
                InetAddress localHost = InetAddress.getLocalHost();
                ipAddress = localHost.getHostAddress();
            } catch (UnknownHostException e) {
                ipAddress = "Unknown";
            }
        }
        return ipAddress;
    }
}