package com.ping.springbootjdbc.repository.impl;

import com.ping.springbootjdbc.model.User;
import com.ping.springbootjdbc.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user =new User("zsp","123",28);
        userRepository.save(user);
    }

    @Test
    public void update() {
        User user =new User("test","123456",28);
        user.setId(1L);
        userRepository.update(user);
    }

    @Test
    public void delete() {

    }

    @Test
    public void findALL() {
        List<User> users=userRepository.findALL();
        for (User user:users){
            System.out.println("user == "+user.toString());
        }
    }

    @Test
    public void findById() {
        User user=userRepository.findById(2L);
        System.out.println("user == "+user.toString());
    }
}