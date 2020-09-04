package com.yjc.system.admin.dto;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/13
 * 所属功能
 */

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserChangePasswordDto extends Model implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @NotNull(message = "登录id不能为空")
    private String id;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "用户密码不能为空")
    private String oldPassword;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "用户密码不能为空")
    private String newPassword;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
