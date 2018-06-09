package com.hd.api.service;

public interface CacheService {

	/**
	 * 清空spring指定缓存
	 * 
	 * @param cacheName
	 */
	void Clear(String cacheName);

	/**
	 * 清空所有缓存
	 */
	void ClearAll();

}
