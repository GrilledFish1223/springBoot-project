package com.ping.springboot.aop.springbootaop.controller;

import com.ping.springboot.aop.springbootaop.annotation.Action;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zhangsp
 * @param:
 * @date: 2018/5/16 16:05
 */
@RestController
public class Hellocontroller {
    @RequestMapping("/")
    @Action("hello")
    public String hello() {
        return "Hello Spring Boot!Hello,The Life!!!";
    }
}

