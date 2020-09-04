package com.yjc.system.commen.entity;

import lombok.Data;

/**
 * 列属性
 * @author lm
 * @date 2019/4/29 11:07
 * @Description TODO
 **/
@Data
public class ColumnEntity {

    /**
     * 列表
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 字符小数长度
     */
    private Integer dataScale;
    /**
     * 字符小数长度
     */
    private Integer dataPrecision;

    /**
     * 备注
     */
    private String comments;

    /**
     * 驼峰属性
     */
    private String caseAttrName;

    /**
     * 普通属性
     */
    private String lowerAttrName;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 其他信息。
     */
    private String extra;
}
