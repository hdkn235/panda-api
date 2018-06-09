package com.hd.api.service.impl;

import com.hd.api.constant.ErrorInfoConst;
import com.hd.api.exception.ServiceException;
import com.hd.api.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private CacheManager cacheManager;

	@Override
	public void Clear(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			throw new ServiceException(ErrorInfoConst.CACHE_NO);
		}
		cache.clear();
	}

	@Override
	public void ClearAll() {
		cacheManager.getCacheNames().stream().map(cacheManager::getCache).forEach(Cache::clear);
	}

}
