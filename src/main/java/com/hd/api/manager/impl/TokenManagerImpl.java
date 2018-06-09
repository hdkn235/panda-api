package com.hd.api.manager.impl;

import com.hd.api.manager.TokenManager;
import com.hd.api.model.Token;
import com.hd.api.utils.common.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis Token提供者
 *
 * @author hoda
 * @since 1.0.0
 */
@Component
public class TokenManagerImpl implements TokenManager {

    @Value("#{api.token_expires_hour}")
    private Integer tokenExpiresHour;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String create(String userId) {
        String token = CommonUtil.createUUID();
        //存储到redis并设置过期时间
        redisTemplate.boundValueOps(token).set(userId, tokenExpiresHour, TimeUnit.HOURS);
        return token;
    }

    @Override
    public boolean verify(Token token) {
        if (token == null) {
            return false;
        }

        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.boundValueOps(token.getTokenId()).expire(tokenExpiresHour, TimeUnit.HOURS);

        return true;
    }

    @Override
    public boolean delete(String token) {
        redisTemplate.delete(token);
        return true;
    }

    @Override
    public Token get(String token) {
        String userId = redisTemplate.boundValueOps(token).get();
        Token tokenModel = null;
        if (StringUtils.isNoneBlank(userId)) {
            tokenModel = new Token();
            tokenModel.setTokenId(token);
            tokenModel.setUserId(userId);
        }
        return tokenModel;
    }
}
