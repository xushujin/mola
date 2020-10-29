package com.mola.bi.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author hatim
 */
@Slf4j
@Order(-1)
@Aspect
@Configuration
public class DataSourceExchangeConfig {

    @Pointcut("@annotation(com.mola.bi.config.TargetDataSource)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            TargetDataSource targetDataSource = signature.getMethod().getAnnotation(TargetDataSource.class);
            DataSourceHolder.setDataSource(targetDataSource.name());
            return pjp.proceed();
        } finally {
            DataSourceHolder.clearDataSource();
        }
    }
}
