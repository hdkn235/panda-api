package com.hd.api.security;

import com.alibaba.fastjson.JSON;
import com.hd.api.constant.Constants;
import com.hd.api.constant.ErrorInfoConst;
import com.hd.api.exception.TokenException;
import com.hd.api.manager.TokenManager;
import com.hd.api.model.Token;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Autowired
	private TokenManager tokenManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info(request.getRequestURI());
		logger.info(request.getRemoteAddr());
		logger.info(JSON.toJSONString(request.getParameterMap()));

		// 从切点上获取目标方法
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		// 若目标方法忽略了安全性检查，则直接调用目标方法
		if (method.isAnnotationPresent(IgnoreSecurity.class)) {
			return true;
		}
		// 从 request header 中获取当前 token
		String token = request.getHeader(Constants.TOKEN_NAME);
		if (StringUtils.isBlank(token)) {
			throw new TokenException(ErrorInfoConst.TOKEN_INVALID);
		}

		// 检查 token 有效性
		Token model = tokenManager.get(token);

		// 验证失败
		if (!tokenManager.verify(model)) {
			throw new TokenException(ErrorInfoConst.TOKEN_INVALID);
		}

		// 如果token验证成功，将token对应的用户id存在request中
		request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId ());

		// 调用目标方法
		return true;
	}
}
