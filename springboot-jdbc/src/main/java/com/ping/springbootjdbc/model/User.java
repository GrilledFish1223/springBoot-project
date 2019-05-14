/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version $Id User.java, v 1.0 2019-05-14 21:10 zsp $$
 * @author: zhangsp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String password;
    private int age;

    public User(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }
}
