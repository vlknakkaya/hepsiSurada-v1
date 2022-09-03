package com.hepsisurada.userservice.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.hepsisurada.userservice.aspect.annotation.Performance;

@Aspect
@Component
public class PerformanceAspect {

	@Around("@annotation(performance)")
	public Object calculateMethodPerformance(ProceedingJoinPoint joinPoint, Performance performance) throws Throwable {
		Logger logger = LogManager.getLogger(joinPoint.getSignature().getDeclaringType());
		String methodName = joinPoint.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();
		Object proceed = joinPoint.proceed();
		stopWatch.stop();
		
		logger.debug("[PERFORMANCE] - {} executed in {} ms", methodName, stopWatch.getTotalTimeMillis());
		
		return proceed;
	}
	
}
