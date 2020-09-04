package com.yjc.system.commen.common.enums;

import org.springframework.util.ClassUtils;

/**
 * 常量
 * @author lm
 * @date 2019/4/19 21:38
 * @Description TODO
 **/

public interface CommonConstants {


    long USER_REDIS_TIME_OUT=20*60*1000;


    /**前端工程名**/
    String FRONT_END_PROJECT = "canteen_ui";

    /**后端工程名**/
    String BACK_END_PROJECT = "canteen";

    /**成功标记**/
    Integer SUCCESS = 200;
    /**菜单**/

    /**失败标记**/
    Integer FAIL = 500;

    /**验证码前缀**/
    String CAPTCH_CODE_KEY = "CAPTCH_CODE_KEY_";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * 默认当前页码
     */
    Integer CURRENT_PAGE = 1 ;

    /**
     * 默认当前页大小
     */
    Integer PAGE_SIZE = 20 ;

    /**
     * 否
     */
    Integer isNo = 0;
    /**
     * 是
     */
    Integer isYes = 1;

    /**
     * ip-redis递增序列参数
     */
    Long ipData=1L;
    /**
     * 项目当前根路径
     */
    String CLASS_PATH=ClassUtils.getDefaultClassLoader().getResource("").getPath();




}
