package com.yjc.system.commen.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/19
 * 所属功能
 */

@Data
public class BaseEntity extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人id
     */
    @TableField(fill=FieldFill.INSERT)
    private String creationUserId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @TableField(fill=FieldFill.INSERT)
    private LocalDateTime creationTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 变更人id
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private String updateUserId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有效 0否1是
     */
    @TableField(fill=FieldFill.INSERT)
    private Integer isValid;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
