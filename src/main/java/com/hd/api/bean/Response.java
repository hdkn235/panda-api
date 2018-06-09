package com.hd.api.bean;

import com.hd.api.constant.CodeEnum;

public class Response {

	/**
	 * 状态码
	 */
	private CodeEnum code;

	/**
	 * 描述信息
	 */
	private String message;

	/**
	 * 返回结果中的resObj
	 */
	private Object data;

	public String getCode() {
		return code.toString();
	}

	public void setCode(CodeEnum code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object resObj) {
		this.data = resObj;
	}

	public Response success() {
		this.code = CodeEnum.SUCCESS;
		this.message = "ok";
		return this;
	}

	public Response success(Object data) {
		this.code = CodeEnum.SUCCESS;
		this.message = "ok";
		this.data = data;
		return this;
	}

	public Response empty() {
		this.code = CodeEnum.SUCCESS;
		this.message = "ok";
		return this;
	}

	public Response failure() {
		this.code = CodeEnum.SYSTEM_FAIL;
		this.message = "error";
		return this;
	}

	public Response failure(String message) {
		this.code = CodeEnum.SYSTEM_FAIL;
		this.message = message;
		return this;
	}

	public Response failure(CodeEnum code, String message) {
		this.code = code;
		this.message = message;
		return this;
	}
}
