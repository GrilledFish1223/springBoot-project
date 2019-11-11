package com.ping.springbootdatajpa.database;

import lombok.Data;

/**
 * @version $Id CommonConstant.java, v 1.0 2019-11-08 下午3:17 zsp $$
 * @author: zsp
 */
@Data
public class CommonConstant {
    /**
     * 主数据源
     */
    public static final String MASTER_DATASOURCE = "masterDruidDataSource";

    /**
     * 从数据源
     */
    public static final String SLAVE_DATASOURCE= "slaveDruidDataSource";
}
