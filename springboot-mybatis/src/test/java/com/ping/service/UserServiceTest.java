package com.ping.service;

import com.ping.basic.BaseTest;
import com.ping.bean.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void records() {
        UserEntity user = userService.selectUser(1);
        Assert.assertEquals(1, user.getId());
        System.out.println(user.getUserSex());
    }
}
