package com.ping.springbootredissession.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version $Id User.java, v 1.0 2019-09-04 10:27 zsp $$
 * @author: zhangsp
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 8412918920756364876L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String regTime;

    public User(String email, String nickname, String password, String userName, String regTime) {
        super();
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.userName = userName;
        this.regTime = regTime;
    }

}
