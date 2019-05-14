/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @version $Id WelcomeController.java, v 1.0 2019-04-24 17:17 zsp $$
 * @author: zhangsp
 */
@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "hello world");
        return "welcome";
    }

    @GetMapping("/user")
    public String user(Map<String, Object> model, HttpServletRequest request) {
        model.put("username", "neo");
        model.put("salary", 666);
        request.getSession().setAttribute("count", 6);
        return "user";
    }

}
