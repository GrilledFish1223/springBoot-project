package com.ping.springbootredissession.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @version $Id SessionConfig.java, v 1.0 2019-09-04 10:26 zsp $$
 * @author: zhangsp
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {
}
