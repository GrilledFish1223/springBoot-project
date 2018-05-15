package com.ping.springboot.config.sprinbootconfig.controller;

import com.ping.springboot.config.sprinbootconfig.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zhangsp
 * @param:
 * @date: 2018/5/15 20:14
 */
@RestController
public class HelloController {
    @Autowired
    private Article article;

    @RequestMapping("/")
    public String hello() {
        return "article's name is " + article.getName() + " and article's author is " + article.getAuthor();
    }
}
