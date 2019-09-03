package com.ping.config;

import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @version $Id RedisConfig.java, v 1.0 2019-09-03 14:33 zsp $$
 * @author: zhangsp
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj: objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

}
