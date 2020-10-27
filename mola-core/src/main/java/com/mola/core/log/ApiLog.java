package com.mola.core.log;

import com.mola.core.consts.enu.RequestStatusEnu;
import com.mola.core.response.failure.MolaException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import java.util.UUID;

/**
 * 接口请求日志AOP，结合swagger注解
 * 日志内容按json串打印会更灵活
 * 这里是不是可以考虑异步日志，但是异步日志会不会影响日志的唯一标识？需要验证下。而且还存在日志丢失风险，慎重！
 *
 * @author hatim
 */
@Slf4j
@Order(1)
@Aspect
public class ApiLog {
    @Value("${api.log.out.print}")
    private boolean outLogPrint;

    private static final String LOG_IN_TITLE = "[入口日志]_@_{}";
    private static final String LOG_OUT_TITLE = "[出口日志]_@_{}";

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        LogData logData = LogData.builder().build();
        // 把UUID加入MD，线索化方便排查问题
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            MDC.put("uuid", uuid);

            MethodSignature signature = (MethodSignature) pjp.getSignature();
            ApiOperation apiOperation = signature.getMethod().getAnnotation(ApiOperation.class);

            String methodDesc = apiOperation == null ? "" : apiOperation.value();
            String methodName = pjp.getSignature().toShortString();

            // 获取参数
            Object[] methodArgs = methodArgs(pjp);

            // 组装日志数据
            logData.setMethodName(methodName).setMethodDesc(methodDesc).setMethodArgs(methodArgs);

            log.info(LOG_IN_TITLE, logData);
            // 执行方法
            logData.setResult(pjp.proceed());
        } catch (Throwable throwable) {
            // 报错则记录信息
            logData.setResult(throwable);
            throw throwable;
        } finally {
            // 请求耗时
            logData.setUseTime(System.currentTimeMillis() - startTime);

            // 功能名称、请求时间、请求结果、请求用户
            // 如果是正常返回则不打印返回值，请求异常才打印返回值
            if (logData.getResult() instanceof Throwable) {
                logData.setStatus(RequestStatusEnu.EXCEPTION.toString());
                logData.setResult(((Throwable) logData.getResult()).getMessage());
                // 这里细分，捕获异常未warn
                if (logData.getResult() instanceof MolaException) {
                    log.warn(LOG_OUT_TITLE, logData);
                } else {
                    log.error(LOG_OUT_TITLE, logData);
                }
            } else {
                logData.setStatus(RequestStatusEnu.SUCCESS.toString());
                if (outLogPrint) {
                    log.info(LOG_OUT_TITLE, logData);
                }
                // MDC清理
                MDC.clear();
            }

        }
        return logData.getResult();
    }

    /**
     * 获取方法参数
     *
     * @return
     */
    private static Object[] methodArgs(ProceedingJoinPoint point) {
        return point.getArgs();
    }
}
