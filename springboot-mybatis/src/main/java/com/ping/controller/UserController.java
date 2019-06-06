package com.ping.controller;

import com.ping.bean.Page;
import com.ping.bean.UserEntity;
import com.ping.bean.UserParam;
import com.ping.mapper.one.UserOneMapper;
import com.ping.mapper.two.UserTwoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserOneMapper userOneMapper;

    @Autowired
    private UserTwoMapper userTwoMapper;

    @RequestMapping("/getUsers")
    public List<UserEntity> getUsers() {
        return userTwoMapper.findAll();
    }

    @RequestMapping("/getUser")
    public UserEntity getUser(Long id) {
        return userTwoMapper.findOne(id);
    }

    @RequestMapping("/add")
    public void save(UserEntity user) {
        userTwoMapper.insert(user);
    }

    @RequestMapping(value="update")
    public void update(UserEntity user) {
        userTwoMapper.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userTwoMapper.delete(id);
    }

    @RequestMapping("/getList")
    public Page getList(UserParam userParam) {
        List<UserEntity> users= userTwoMapper.getList(userParam);
        long count= userTwoMapper.getCount(userParam);
        return new Page(userParam,count,users);
    }
}
