package com.yjc.system.commen.entity;

import lombok.Data;

/**
 * 生成配置
 * @author lm
 * @date 2019/4/29 11:08
 * @Description TODO
 **/
@Data
public class GenConfigEntity {

    /**
     * 包名
     */
    private String packageName;

    /**
     * 作者
     */
    private String author;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表备注
     */
    private String comments;
}
