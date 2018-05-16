package com.ping.springboot.properties.springbootproperties.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zhangsp
 * @param:
 * @date: 2018/5/16 15:35
 */
@RestController
public class HelloController {
    @Value("${article.author}")
    private String articleAuthor;
    @Value("${article.name}")
    private String getArticleName;
    @RequestMapping("/")
    public String hello() {
        return "article name is: " + getArticleName + ",and article author is: " + articleAuthor;
    }

}
