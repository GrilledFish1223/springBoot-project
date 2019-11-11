package com.ping.springbootdatajpa.database;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 *
 * @version $Id DynamicDataSourceConfig.java, v 1.0 2019-11-08 下午3:30 zsp $$
 * @author: zsp
 */
@Slf4j
@Configuration
public class DynamicDataSourceConfig {
    /**
     * 创建 TargetDataSource Bean
     */
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(CommonConstant.MASTER_DATASOURCE, masterDataSource);
        targetDataSources.put(CommonConstant.SLAVE_DATASOURCE, slaveDataSource);
        log.info("^o^= DataSources:" + targetDataSources);
        //默认的数据源是oneDataSource
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
}
