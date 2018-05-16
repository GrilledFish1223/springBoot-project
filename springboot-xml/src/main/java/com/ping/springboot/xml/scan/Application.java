package com.ping.springboot.xml.scan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: Zhangsp
 * @param:
 * @date: 2018/5/16 15:21
 */
@SpringBootApplication
@ImportResource(value = {"classpath:application-bean.xml"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
