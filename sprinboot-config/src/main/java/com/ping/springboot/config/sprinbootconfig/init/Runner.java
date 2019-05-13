/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springboot.config.sprinbootconfig.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @version $Id Runner.java, v 1.0 2019-05-13 14:08 zsp $$
 * @author: zhangsp
 */
@Component
@Order(2)
public class Runner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("-------------------------The Runner start to initialize...");
    }
}
