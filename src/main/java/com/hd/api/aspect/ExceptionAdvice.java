package com.hd.api.aspect;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hd.api.constant.CodeEnum;
import com.hd.api.exception.ServiceException;
import com.hd.api.exception.TokenException;
import com.hd.api.bean.Response;
import com.hd.api.context.WebContext;

/**
 * 用于处理所有的异常情况
 *
 * @author hoda
 * @since 1.0.0
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

	private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		logErrorInfo("缺少请求参数", e);
		return new Response().failure("required_parameter_is_not_present");
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logErrorInfo("参数解析失败", e);
		return new Response().failure("could_not_read_json");
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		logErrorInfo("参数验证失败", e);
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s:%s", field, code);
		return new Response().failure(message);
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public Response handleBindException(BindException e) {
		logErrorInfo("参数绑定失败", e);
		BindingResult result = e.getBindingResult();
		FieldError error = result.getFieldError();
		String field = error.getField();
		String code = error.getDefaultMessage();
		String message = String.format("%s:%s", field, code);
		return new Response().failure(message);
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Response handleServiceException(ConstraintViolationException e) {
		logErrorInfo("参数验证失败", e);
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		ConstraintViolation<?> violation = violations.iterator().next();
		String message = violation.getMessage();
		return new Response().failure("parameter:" + message);
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public Response handleValidationException(ValidationException e) {
		logErrorInfo("参数验证失败", e);
		return new Response().failure("validation_exception");
	}

	/**
	 * 401 - Unauthorized
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(TokenException.class)
	public Response handleTokenException(TokenException e) {
		logErrorInfo("令牌验证失败", e);
		return new Response().failure(CodeEnum.INVALID_TOKEN, "token_is_invalid");
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		logErrorInfo("不支持当前请求方法", e);
		return new Response().failure("request_method_not_supported");
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public Response handleHttpMediaTypeNotSupportedException(Exception e) {
		logErrorInfo("不支持当前媒体类型", e);
		return new Response().failure("content_type_not_supported");
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ServiceException.class)
	public Response handleServiceException(ServiceException e) {
		logErrorInfo("服务异常", e);
		return new Response().failure(CodeEnum.SYSTEM_ERROR, e.getMessage());
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e) {
		logErrorInfo("通用异常", e);
		return new Response().failure(CodeEnum.SYSTEM_ERROR, "unknown_exception");
	}

	/**
	 * 记录错误日志
	 * 
	 * @param desc
	 * @param e
	 */
	public void logErrorInfo(String desc, Exception e) {
		String requestInfo = WebContext.getRequestInfo();
		String resultStr = String.format("请求信息：%s，错误描述：%s", requestInfo, desc);
		logger.error(resultStr, e);
	}
}
