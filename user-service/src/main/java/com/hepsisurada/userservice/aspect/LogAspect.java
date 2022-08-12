package com.hepsisurada.userservice.aspect;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import com.hepsisurada.userservice.aspect.annotation.Log;

@Aspect
@Component
public class LogAspect {

	@Around("@annotation(log)")
	public Object logMethod(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
		Logger logger = LogManager.getLogger(joinPoint.getSignature().getDeclaringType());
		String methodName = joinPoint.getSignature().getName();
		Map<String, Object> parameters = obtainParameters(joinPoint);
		
        logger.debug("{} started with parameters: {}", methodName, parameters);
        
        Object proceed;
        
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            logger.debug("{} failed with exception: {}", methodName, e);
            throw e;
        }
        
        logger.debug("{} finished with return value: {}", methodName, proceed);
        
        return proceed;
	}

    private Map<String, Object> obtainParameters(ProceedingJoinPoint joinPoint) {
        Map<String, Object> parameters = new HashMap<>();
        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();
        for (int i=0; i < parameterNames.length && i < parameterValues.length; i++) {
            parameters.put(parameterNames[i], parameterValues[i]);
        }
        return parameters;
    }
	
}
