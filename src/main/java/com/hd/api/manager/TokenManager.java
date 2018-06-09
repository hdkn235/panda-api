package com.hd.api.manager;

import com.hd.api.model.Token;

/**
 * Token 提供者
 * 
 * @author hoda
 *
 */
public interface TokenManager {

	/**
	 * 获取token实体
	 * 
	 * @param token
	 * @return
	 */
	Token get(String token);

	/**
	 * 新增token
	 * 
	 * @param userId
	 * @return
	 */
	String create(String userId);

	/**
	 * 检查token有效并续期
	 * 
	 * @param token
	 * @return
	 */
	boolean verify(Token token);

	/**
	 * 根据token删除
	 * 
	 * @param token
	 * @return
	 */
	boolean delete(String token);

}
