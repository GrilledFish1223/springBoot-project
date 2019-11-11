package com.ping.springbootdatajpa.database;

import java.lang.annotation.*;

/**
 * 动态切换数据源注解
 *
 * @version $Id TargetDataSource.java, v 1.0 2019-11-08 下午3:18 zsp $$
 * @author: zsp
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
    String value() default CommonConstant.MASTER_DATASOURCE;
//    //代码切换到数据源
//    DynamicDataSource.setDataSource(CommonConstant.MASTER_DATASOURCE);
//    DynamicDataSource.clearDataSource();

}
