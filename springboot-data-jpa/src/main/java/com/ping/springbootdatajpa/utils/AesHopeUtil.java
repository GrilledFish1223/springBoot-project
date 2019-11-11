package com.ping.springbootdatajpa.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加解密工具类
 *
 * @version $Id AesHopeUtil.java, v 1.0 2019-11-08 下午3:08 zsp $$
 * @author: zsp
 */
@Slf4j
public class AesHopeUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /***
     * AES加密
     * @param password
     * @param content
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String content){
        //创建密码器
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            //初始化密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(password));
            //加密
            byte[] bytes1 = cipher.doFinal(bytes);
            //通过BASE64转码返回
            return Base64.encodeBase64String(bytes1);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
                | InvalidKeyException e) {
            log.error("{}",e);
        }
        return null;
    }

    /***
     * AES解密
     * @param password
     * @param content
     * @return
     * @throws Exception
     */
    public static String decryt(String password, String content) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化解密
            cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec(password));
            byte[] bytes = cipher.doFinal(Base64.decodeBase64(content));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException
                | InvalidKeyException e) {
            log.error("{}",e);
        }
        return null;
    }

    /***
     * 生成加密密钥
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static SecretKeySpec getSecretKeySpec(final String password)  {
        //返回密钥生成器对象
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            //设置AES密钥长度
            keyGenerator.init(128, secureRandom);
            //生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            //转换为AES密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            log.error("{}",e);
        }
        return null;
    }
}
