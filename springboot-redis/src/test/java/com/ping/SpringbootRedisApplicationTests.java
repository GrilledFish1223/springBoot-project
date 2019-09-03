package com.ping;

import com.ping.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testString() {
        redisTemplate.opsForValue().set("ping", "ityoudontknow");
        Assert.assertEquals("ityoudontknow", redisTemplate.opsForValue().get("ping"));
    }

    @Test
    public void testObj() throws InterruptedException{
        User user = User.builder().id(2020L).userName("ping").password("123456").email("ityouknow@126.com")
                .nickname("rf").regTime("20190903").build();
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("ping", user, 100, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("ping");
        if (exists) {

            User u = operations.get("ping");
            assert u != null;
            System.out.println("user:" + u.toString());
        } else {
            System.out.println("exists is false");

        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash", "you", "ypou");
        String value = (String)hash.get("hash", "you");
        System.out.println("hash value: " + value);
    }

    @Test
    public void testList() {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.leftPush("list", "it");
        list.leftPush("list", "you");
        list.leftPush("list", "know");

        String value = list.leftPop("list");
        assert value != null;
        System.out.println("list value: " + value.trim());
    }

    @Test
    public void testSet() {
        String key="set";
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key,"it");
        set.add(key,"you");
        set.add(key,"you");
        set.add(key,"know");
        Set<String> values = set.members(key);
        assert values != null;
        for (String v: values) {
            System.out.println("set value :" + v);
        }

    }
}

