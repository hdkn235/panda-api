package com.hd.api.exception;

/**
 * token相关异常
 * 
 * @author hoda
 *
 */
public class TokenException extends RuntimeException {

	static final long serialVersionUID = 7581922433167879245L;

	public TokenException(String message) {
		super(message);
	}
}
