package com.yjc.system.commen.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lm
 * @date 2019/7/5 17:48
 * @Description TODO
 **/
@AllArgsConstructor
@Getter
public enum WhetherEnum {

    NO(0,0,"0","否"),
    YES(1,1,"1","是");
    private int code;
    private Integer integercode;
    private String strcode;
    private String desc;

    /**
     * 获取描述
     * @param desc
     * @return
     */
    public static Integer getCodeByDesc(String desc){
        if(StrUtil.isEmpty(desc)){
            return null;
        }
        for (WhetherEnum c : WhetherEnum.values()) {
            if (c.getDesc().equalsIgnoreCase(desc) ) {
                return c.code;
            }
        }
        return null;
    }
}
