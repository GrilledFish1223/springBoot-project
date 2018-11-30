package com.ping.mapper;

import com.ping.bean.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserEntityDao {

    @Select("SELECT * FROM user_entity WHERE id = #{id}")
    UserEntity selectUser(@Param("id") int id);
}
