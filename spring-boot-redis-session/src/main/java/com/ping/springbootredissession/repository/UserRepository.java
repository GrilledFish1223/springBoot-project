package com.ping.springbootredissession.repository;

import com.ping.springbootredissession.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version $Id UserRepository.java, v 1.0 2019-09-04 10:38 zsp $$
 * @author: zhangsp
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userNmae);
}
