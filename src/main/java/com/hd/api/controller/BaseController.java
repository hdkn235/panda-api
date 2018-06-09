package com.hd.api.controller;

import com.hd.api.constant.ErrorInfoConst;
import com.hd.api.exception.ServiceException;
import com.hd.api.model.Userinfo;
import com.hd.api.service.UserInfoService;
import com.hd.app.config.Constants;
import com.hd.app.context.WebContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

	/**
	 * 日志
	 */
	protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private UserInfoService uiService;

	/**
	 * 获取用户登录信息
	 * 
	 * @return
	 */
	protected Userinfo getUserInfo() {
		return uiService.get(getUserId());
	}

	/**
	 * 获取用户id
	 * 
	 * @return
	 */
	protected String getUserId() {
		Object userId = WebContext.getRequest().getAttribute(Constants.CURRENT_USER_ID);
		if (userId == null){
			throw  new ServiceException(ErrorInfoConst.USER_NOT_EXIST);
		}
		return userId.toString();
	}

	/**
	 * 获取token
	 * 
	 * @return
	 */
	protected String getToken() {
		return WebContext.getRequest().getHeader(Constants.TOKEN_NAME);
	}
}
