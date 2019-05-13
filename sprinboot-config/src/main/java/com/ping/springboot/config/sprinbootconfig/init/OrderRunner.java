/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springboot.config.sprinbootconfig.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @version $Id OrderRunner.java, v 1.0 2019-05-13 14:57 zsp $$
 * @author: zhangsp
 */
@Component
@Order(1)
public class OrderRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("The OrderRunner1 start to initialize ...");
    }
}
