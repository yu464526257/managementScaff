package com.yjc.system.commen.entity;

import lombok.Data;

import java.util.List;

/**
 * 表属性
 * @author lm
 * @date 2019/4/29 11:08
 * @Description TODO
 **/
@Data
public class TableEntity {

    /**
     * 名称
     */
    private String tableName;

    /**
     * 备注
     */
    private String comments;

    /**
     * 主键
     */
    private ColumnEntity pk;

    /**
     * 列名
     */
    private List<ColumnEntity> columns;

    /**
     * 驼峰类型
     */
    private String caseClassName;

    /**
     * 普通类型
     */
    private String lowerClassName;
}
