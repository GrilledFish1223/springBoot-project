package com.ping.springboot.config.sprinbootconfig;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SprinbootConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprinbootConfigApplication.class, args);
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
