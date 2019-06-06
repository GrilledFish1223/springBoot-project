package com.ping.mapper;

import com.mysql.cj.util.StringUtils;
import com.ping.bean.UserParam;
import org.apache.ibatis.jdbc.SQL;

/**
 * @version $Id UserSql.java, v 1.0 2019-06-06 18:25 zsp $$
 * @author: zhangsp
 */

public class UserSql {
    public String  getList(UserParam userParam) {
        StringBuilder sql = new StringBuilder("select id, userName, passWord, user_sex as userSex, nick_name as nickName" +
                "from users where 1=1 ");
        if (userParam!= null) {
            if (!StringUtils.isNullOrEmpty(userParam.getUserName())) {
                sql.append(" and userName= #{userName}");
            }

            if (!StringUtils.isNullOrEmpty(userParam.getUserSex())) {
                sql.append(" and user_sex = #{userSex}");
            }

        }
        sql.append(" order by id desc limit ").append(userParam.getBeginLine()).append(",").append(userParam.getPageSize());
        return sql.toString();
    }

    public String getCount(UserParam userParam) {

        return new SQL(){{
            SELECT("count(1)");
            FROM("users");
            if (!StringUtils.isNullOrEmpty(userParam.getUserName())) {
                WHERE("userName = #{userName}");
            }
            if (!StringUtils.isNullOrEmpty(userParam.getUserSex())) {
                WHERE("user_sex = #{userSex}");
            }
            //从这个toString可以看出，其内部使用高效的StringBuilder实现SQL拼接
        }}.toString();
    }
}
