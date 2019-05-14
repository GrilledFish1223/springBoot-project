package com.ping.springboot.config.sprinbootconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author zhangsp
 * @date 2016/10/31
 */
@SpringBootApplication
@EnableScheduling
public class SpringbootConfigApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootConfigApplication.class);
    public static void main(String[] args) {
        System.out.println("The Service to start");
        SpringApplication.run(SpringbootConfigApplication.class, args);
        System.out.println("---------The Service has started-------------------");
        // SpringApplication.run(HelloApplication.class, args);
        /**
         * 关闭Banner方法一
         */
        // SpringApplication application = new
        // SpringApplication(HelloApplication.class);
        // application.setBannerMode(Banner.Mode.OFF);
        // application.run(args);

        /**
         * 关闭Banner方法二
         */
       // new SpringApplicationBuilder(Application.class).bannerMode(Banner.Mode.OFF).run(args);
    }
}
