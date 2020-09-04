package com.yjc.system.commen.common.file.entity;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/21
 * 所属功能
 */

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjc.system.commen.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("FILE_PASH")
public class FilePath extends BaseEntity {
    /**
     * 图片id
     */
    @ApiModelProperty(value = "图片id")
    private String id;
    /**
     * 图片id
     */
    @ApiModelProperty(value = "图片来源编码")
    private Integer comeCode;
    /**
     * 图片id
     */
    @ApiModelProperty(value = "图片地址")
    private String url;
    /**
     * 图片id
     */
    @ApiModelProperty(value = "图片名称")
    private String name;



}
