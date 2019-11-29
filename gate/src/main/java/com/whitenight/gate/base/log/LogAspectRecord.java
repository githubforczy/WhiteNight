package com.whitenight.gate.base.log;

import com.whitenight.gate.base.annotation.WnLog;
import com.whitenight.gate.utils.JSONUtil;
import io.micrometer.core.instrument.util.StringUtils;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspectRecord {

    @Around("within(com.whitenight.gate..*) && @annotation(com.whitenight.gate.base.annotation.WnLog)")
    public Object addLogger(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Method targetMethod = targetClass.getMethod(methodName, parameterTypes);
        WnLog wnLog = targetMethod.getAnnotation(WnLog.class);

        printLog("入参",wnLog,joinPoint.getArgs());
        Object result = joinPoint.proceed();
        printLog("出参",wnLog,result);
        return result;
    }

    private void printLog(String argDesc, WnLog wnLog, Object args){
        String logLevel = wnLog.level().toUpperCase();
        String logValue = wnLog.value();
        if(StringUtils.isNotEmpty(logValue)){
            argDesc = logValue + "," + argDesc;
        }

        switch (logLevel) {
            case "TRACE":
                if (log.isTraceEnabled()) {
                    log.trace(argDesc + JSONUtil.toJsonString(args));
                }
                break;
            case "DEBUG":
                if (log.isDebugEnabled()) {
                    log.debug(argDesc + JSONUtil.toJsonString(args));
                }
                break;
            case "INFO":
                if (log.isInfoEnabled()) {
                    log.info(argDesc + JSONUtil.toJsonString(args));
                }
                break;
            case "WARN":
                if (log.isWarnEnabled()) {
                    log.warn(argDesc + JSONUtil.toJsonString(args));
                }
                break;
            case "ERROR":
                if (log.isErrorEnabled()) {
                    log.error(argDesc + JSONUtil.toJsonString(args));
                }
                break;

            default:
                log.info("日志级别不存在：{}", logLevel);
        }
    }

}
