package com.ping.springboot.aop.springbootaop.annotation;

import java.lang.annotation.*;

/**
 * @author: Zhangsp
 * @param:
 * @date: 2018/5/16 15:59
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String value() default "";
}
