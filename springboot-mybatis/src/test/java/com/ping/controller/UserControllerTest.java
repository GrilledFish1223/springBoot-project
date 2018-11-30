package com.ping.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTest extends BaseControllerTest<UserController> {
    @Autowired
    private UserController userController;

    @Before
    public void setUp() {
        createMockMvc(userController);
    }

    @Test
    public void records() throws Exception {
        String url = "/selectUser/1";
        ControllerTestResult result = this.get(url);
        int status = result.getStatus();
        Assert.assertEquals(200,status);
        System.out.println(result.getContent());
    }
}
