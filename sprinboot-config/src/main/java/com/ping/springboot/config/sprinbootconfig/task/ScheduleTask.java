/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springboot.config.sprinbootconfig.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @version $Id ScheduleTask.java, v 1.0 2019-05-13 16:37 zsp $$
 * @author: zhangsp
 */
@Component
public class ScheduleTask {
    private int count = 0;

    @Scheduled(cron = "*/6 * * * * ?")
    private void process() {
        System.out.println("this is scheduler task runing  " +(count++));
    }
}
