package com.ping.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @version $Id RedisService.java, v 1.0 2019-09-03 17:32 zsp $$
 * @author: zhangsp
 */
@Service
public class RedisService {
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("set error: key{}, value: {}", key, value, e);
        }
        return result;
    }
}
