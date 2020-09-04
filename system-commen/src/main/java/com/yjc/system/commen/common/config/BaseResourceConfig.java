package com.yjc.system.commen.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 公共资源
 * @author 于峻成
 * @date 2019/7/2 20:43
 * @Description TODO
 **/
@Configuration
public class BaseResourceConfig {

    /**
     * 输入参数 是否加密
     */
    public static boolean inputEncrypt;
    /**
     * 输出数据 是否加密
     */
    public static boolean outputEncrypt;
    /**
     * aes加密key
     */
    public static String encodeKey;
    @Value("${base.encrypt.key:1234567812345678}")
    public void setEncodeKey(String encodeKey) {
        BaseResourceConfig.encodeKey=encodeKey;
    }
    @Value("${base.encrypt.inputEncrypt}")
    public void setInputEncrypt(boolean inputEncrypt) {
        BaseResourceConfig.inputEncrypt=inputEncrypt;
    }

    @Value("${base.encrypt.outputEncrypt}")
    public void setOutputEncrypt(boolean outputEncrypt) {
        BaseResourceConfig.outputEncrypt=outputEncrypt;
    }
}
