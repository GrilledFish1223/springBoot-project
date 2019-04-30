/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootthymeleaf.controller;

import com.ping.springbootthymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @version $Id ExampleController.java, v 1.0 2019-04-30 16:01 zsp $$
 * @author: zhangsp
 */
@Controller
public class ExampleController {
    @GetMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "it you known");
        return "string";
    }

    @GetMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }

    @GetMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    @GetMapping("/inline")
    public String inline(ModelMap map) {
        map.addAttribute("userName", "Ping");
        return "inline";
    }

    @GetMapping("/object")
    public String object(HttpServletRequest request) {
        request.setAttribute("request","a new request");
        request.getSession().setAttribute("session", "a new session");
        return "object";
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<>();
        User user1=new User("大牛",12,"123456");
        User user2=new User("小牛",6,"123563");
        User user3=new User("纯洁的微笑",66,"666666");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }
}
