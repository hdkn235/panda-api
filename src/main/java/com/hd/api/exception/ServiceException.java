package com.hd.api.exception;

import com.hd.api.constant.CodeEnum;

/**
 * 业务相关异常
 * 
 * @author hoda
 *
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -1554583253509594737L;

	private CodeEnum errCode;

	private String errMessage;

	public ServiceException(String message) {
		super(message);
		this.errCode = CodeEnum.SYSTEM_FAIL;
		this.errMessage = message;
	}

	public ServiceException(CodeEnum code, String message) {
		super(message);
		this.errCode = code;
		this.errMessage = message;
	}

	public String getErrCode() {
		return errCode.toString();
	}

	public String getErrMessage() {
		return errMessage;
	}

}
