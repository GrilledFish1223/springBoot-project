/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootjdbc.repository;

import com.ping.springbootjdbc.model.User;

import java.util.List;

/**
 * @author zsp
 * @version $Id UserRepository.java, v 1.0 2019-05-14 21:11 zsp Vl $$
 */
public interface UserRepository {
    int save(User user);

    int update(User user);

    int delete(long id);

    List<User> findALL();

    User findById(long id);
}
