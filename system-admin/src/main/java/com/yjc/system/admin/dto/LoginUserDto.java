package com.yjc.system.admin.dto;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/14
 * 所属功能
 */

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginUserDto extends Model<LoginUserDto> {

    /**
     * 用户登录账号
     */
    @ApiModelProperty(value = "用户登录账号")
    @NotNull(message = "登录账户不能为空")
    private String userLoginNo;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "登录账户不能为空")
    private String password;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
