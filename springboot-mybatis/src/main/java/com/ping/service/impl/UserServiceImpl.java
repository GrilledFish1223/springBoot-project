package com.ping.service.impl;

import com.ping.bean.UserEntity;
import com.ping.mapper.UserEntityDao;
import com.ping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityDao userDao;

    @Override
    public UserEntity selectUser(int id) {
        return userDao.selectUser(id);
    }

    @Override
    public int insert(UserEntity u) {
        return userDao.insert(u);
    }
}
