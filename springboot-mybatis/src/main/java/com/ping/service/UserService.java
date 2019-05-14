package com.ping.service;

import com.ping.bean.UserEntity;

public interface UserService {
    UserEntity selectUser(int id);

    int insert(UserEntity u);
}
