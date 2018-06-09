package com.hd.api.aspect;

import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

	private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("@annotation(com.hd.api.aspect.ServiceLog)")
	public void pointcut() {

	}

	@Before("pointcut()")
	public void logMethodInvokeParam(JoinPoint joinPoint) throws Exception {
		String methodName = getMethodName(joinPoint);
		String desc = getServiceMethodDescription(joinPoint);
		String params = getParams(joinPoint);

		logger.info("---Bfter method {} invoke,description: {}, params: {}---", methodName, desc, params);
	}

	@AfterReturning(pointcut = "pointcut()", returning = "retVal")
	public void logMethodInvokeResult(JoinPoint joinPoint, Object retVal) throws Exception {
		String methodName = getMethodName(joinPoint);
		logger.info("---After method {} invoke, result: {}---", methodName, retVal.toString());
	}

	public String getMethodName(JoinPoint joinPoint) {
		return joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
	}
	
	public String getParams(JoinPoint joinPoint) {
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				params += ObjectUtils.defaultIfNull(joinPoint.getArgs()[i], "null") + ";";
			}
		}
		return params;
	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 *
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(ServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}

}
