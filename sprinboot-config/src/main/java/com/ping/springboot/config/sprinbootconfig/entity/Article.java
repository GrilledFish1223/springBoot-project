package com.ping.springboot.config.sprinbootconfig.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Zhangsp
 * @param:
 * @date: 2018/5/15 20:24
 */
@Component
@ConfigurationProperties(prefix = "article")
public class Article {
    private String name;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
