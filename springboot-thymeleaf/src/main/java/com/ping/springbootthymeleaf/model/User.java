/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @version $Id User.java, v 1.0 2019-04-30 16:51 zsp $$
 * @author: zhangsp
 */
@Data
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private String pass;
}
