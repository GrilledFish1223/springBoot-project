/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootjdbc.repository;

import com.ping.springbootjdbc.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author zsp
 * @version $Id UserRepository.java, v 1.0 2019-05-14 21:11 zsp Vl $$
 */

public interface UserRepository {
    int save(User user, JdbcTemplate jdbcTemplate);

    int update(User user, JdbcTemplate jdbcTemplate);

    int delete(long id, JdbcTemplate jdbcTemplate);

    List<User> findALL(JdbcTemplate jdbcTemplate);

    User findById(long id, JdbcTemplate jdbcTemplate);
}
