/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.springbootjdbc.repository.impl;

import com.ping.springbootjdbc.model.User;
import com.ping.springbootjdbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @version $Id UserRepositoryImpl.java, v 1.0 2019-05-14 21:12 zsp $$
 * @author: zhangsp
 */
//@Repository(value = "userRepositoryImpl")
//public class UserRepositoryImpl implements UserRepository {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public int save(User user) {
//        return jdbcTemplate.update("INSERT INTO users(name, password, age) values (?, ?, ?)",
//                user.getName(), user.getPassword(), user.getAge());
//    }
//
//    @Override
//    public int update(User user) {
//        return jdbcTemplate.update("UPDATE users SET name = ? , password = ? , age = ? WHERE id=?",
//                user.getName(), user.getPassword(), user.getAge(), user.getId());
//    }
//
//    @Override
//    public int delete(long id) {
//        return jdbcTemplate.update("DELETE FROM users where id = ? ",id);
//    }
//
//    @Override
//    public List<User> findALL() {
//        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
//    }
//
//    @Override
//    public User findById(long id) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[] { id }, new BeanPropertyRowMapper<>(User.class));
//
//    }
//
//    class UserRowMapper implements RowMapper<User> {
//        @Override
//        public User mapRow(ResultSet rs, int i) throws SQLException {
//            User user = new User();
//            user.setId(rs.getLong("id"));
//            user.setName(rs.getString("name"));
//            user.setPassword(rs.getString("password"));
//            user.setAge(rs.getInt("age"));
//            return user;
//        }
//    }
//}
