package com.yjc.system.commen.common.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AES加密
 * @author lm
 * @date 2019/9/2 17:37
 * @Description TODO
 **/
@Slf4j
@Component
public class AesUtils {

    private static final String IV_KEY = "0000000000000000";
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密
     * @param data 数据
     * @return
     */
    public static String encrypt(String data,String key) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding,
                new SecretKeySpec(key.getBytes(), KEY_ALGORITHM),
                new IvParameterSpec(key.getBytes()));
        byte[] result = aes.encrypt(data.getBytes(StandardCharsets.UTF_8));
        return cn.hutool.core.codec.Base64.encode(result,StandardCharsets.UTF_8);
    }

    /**
     * 解密
     * @param data
     * @return
     */
    public static String decrypt(String data,String key) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding,
                new SecretKeySpec(key.getBytes(), KEY_ALGORITHM),
                new IvParameterSpec(key.getBytes()));
        byte[] result = aes.decrypt(cn.hutool.core.codec.Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
        return new String(result, StandardCharsets.UTF_8);
    }

}
