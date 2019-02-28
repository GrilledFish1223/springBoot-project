package com.ping.kafka.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import javax.swing.*;

/**
 * @author: zhangsp
 * @date: 2019/2/28 19:56
 * @copyright: @2019
 */
@Component
public class SpringUtils implements ApplicationContextAware, EmbeddedValueResolverAware {
    private static ApplicationContext applicationContext;
    private static StringValueResolver stringValueResolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        if (SpringUtils.stringValueResolver == null) {
            SpringUtils.stringValueResolver = stringValueResolver;
        }
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}
