package com.ping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version $Id User.java, v 1.0 2019-09-03 15:10 zsp $$
 * @author: zhangsp
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String nickname;
    private String regTime;
}
