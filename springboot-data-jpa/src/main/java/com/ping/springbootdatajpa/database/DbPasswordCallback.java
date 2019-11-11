package com.ping.springbootdatajpa.database;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.ping.springbootdatajpa.utils.AesHopeUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * @version $Id DbPasswordCallback.java, v 1.0 2019-11-08 下午3:12 zsp $$
 * @author: zsp
 */
public class DbPasswordCallback extends DruidPasswordCallback {
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String password = properties.getProperty("password");
        String publicKey = properties.getProperty("publicKey");
        if (StringUtils.isNotEmpty(password)) {
            try {
                //所以这里的代码是将密码进行解密
                String sourcePassword = AesHopeUtil.decryt(publicKey, password);
                assert sourcePassword != null;
                setPassword(sourcePassword.toCharArray());
            } catch (Exception e) {
                setPassword(password.toCharArray());
            }
        }
    }

    /**
     * 生成加密后的密码，放到yml中
     * @param args
     */
    public static void main(String[] args) {
        // 生成加密后的密码，放到yml中
        String password = "gourd123";
        String pwd = AesHopeUtil.encrypt("GOURD-HXNLYW-201314",password);
        System.out.println(pwd);

        String source = AesHopeUtil.decryt("GOURD-HXNLYW-201314",pwd);
        System.out.println(source);
    }
}
