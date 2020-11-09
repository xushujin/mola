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
import org.springframework.util.StopWatch;

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
public class ApiLogConfig {
    @Value("${api.log.out.print}")
    private boolean outLogPrint;
    @Value("${api.log.result.print}")
    private boolean resultPrint;

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch("took time");
        stopWatch.start("before");
        LogData logData = LogData.builder().resultPrint(resultPrint).build();
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
            log.info(logData.setHead(LogData.LOG_IN_HEAD).toString());
            stopWatch.stop();
            stopWatch.start(methodDesc);
            // 执行方法
            logData.setResult(pjp.proceed());
        } catch (Throwable throwable) {
            // 报错则记录信息
            logData.setResult(throwable);
            throw throwable;
        } finally {
            stopWatch.stop();
            stopWatch.start("after");
            // 请求耗时
            logData.setUseTime(stopWatch.getLastTaskTimeMillis());

            // 功能名称、请求时间、请求结果、请求用户
            // 如果是正常返回则不打印返回值，请求异常才打印返回值
            if (logData.getResult() instanceof Throwable) {
                logData.setStatus(RequestStatusEnu.EXCEPTION.toString());
                logData.setResult(((Throwable) logData.getResult()).getMessage());
                // 这里细分，捕获异常未warn
                if (logData.getResult() instanceof MolaException) {
                    log.warn(logData.setHead(LogData.LOG_OUT_HEAD).toString());
                } else {
                    log.error(logData.setHead(LogData.LOG_OUT_HEAD).toString());
                }
                stopWatch.stop();
                log.info(stopWatch.toString());
            } else {
                logData.setStatus(RequestStatusEnu.SUCCESS.toString());
                if (outLogPrint) {
                    log.info(logData.setHead(LogData.LOG_OUT_HEAD).toString());
                }
                stopWatch.stop();
                log.info(stopWatch.toString());
                // MDC清理 TODO 因为catch那里有throw动作，finally结束后会被异常捕获，所以MDC.clear只能在正常返回后执行，以及异常处理后执行
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
