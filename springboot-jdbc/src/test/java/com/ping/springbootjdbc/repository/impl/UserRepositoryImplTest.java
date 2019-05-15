package com.ping.springbootjdbc.repository.impl;

import com.ping.springbootjdbc.model.User;
import com.ping.springbootjdbc.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;
    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void save() {
        User user =new User("zsp","123",28);
        userRepository.save(user, primaryJdbcTemplate);
        userRepository.save(user, secondaryJdbcTemplate);
    }

    @Test
    public void update() {
        User user =new User("test","123456",28);
        user.setId(1L);
        userRepository.update(user, primaryJdbcTemplate);
        userRepository.update(user, secondaryJdbcTemplate);
    }

    @Test
    public void delete() {

    }

    @Test
    public void findALL() {
        List<User> users=userRepository.findALL(primaryJdbcTemplate);
        List<User> users1=userRepository.findALL(secondaryJdbcTemplate);
        for (User user:users){
            System.out.println("user == "+user.toString());
        }

        for (User user:users1){
            System.out.println("user == "+user.toString());
        }
    }

    @Test
    public void findById() {
        User user=userRepository.findById(2L, primaryJdbcTemplate);
        User user1=userRepository.findById(2L, secondaryJdbcTemplate);
        System.out.println("user == "+user.toString());
        System.out.println("user1 == "+user1.toString());
    }
}