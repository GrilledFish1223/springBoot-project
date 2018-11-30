package com.ping.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String userName;
    private String passWord;
    private String userSex;
    private String nickName;
}
