package com.ping;

import com.ping.service.UserService;
import com.ping.bean.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {

    @Resource
    private UserService uerService;

    @Test
    public void testUserService() {
        UserEntity user = UserEntity.builder()
                .userName("df")
                .userSex("女")
                .nickName("heng")
                .passWord("12345")
                .id(56)
                .build();
        System.out.println(user);

        uerService.selectUser(1);
        System.out.println("查询结果：" + user.getUserName() + "\t" + user.getUserSex());
    }

}
