package com.ping.mapper.test;

import com.ping.bean.UserEntity;
import com.ping.enums.UserSexEnum;
import com.ping.mapper.two.UserTwoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTwoMapperTest {
    @Autowired
    private UserTwoMapper userTwoMapper;

    @Test
    public void testInsert() throws Exception {
        userTwoMapper.insert(new UserEntity("aaa","12345", UserSexEnum.MAN));
        userTwoMapper.insert(new UserEntity("bbbb","123456", UserSexEnum.WOMAN));
    }
}