package com.ping.controller;

import com.ping.bean.UserEntity;
import com.ping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/selectUser/{id}")
    public UserEntity selectUser(@PathVariable("id") int id) {
        UserEntity user = userService.selectUser(id);
        logger.info("访问selectUser/{userId}接口，参数userId = " + "userId");
        return user;
    }

}
