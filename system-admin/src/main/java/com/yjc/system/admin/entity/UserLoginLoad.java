package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户登录记录表


 *
 * @author yjc
 * @date 2020-06-29 13:45:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("USER_LOGIN_LOAD")
public class UserLoginLoad extends Model<UserLoginLoad> {
private static final long serialVersionUID = 1L;

  /**
   * $column.comments
   */
  @TableId
  @ApiModelProperty(value = "id")
  private String id;
  /**
   * $column.comments
   */
  @ApiModelProperty(value = "用户名称")
  private String userName;
  /**
   * $column.comments
   */
  @ApiModelProperty(value = "用户编号（登录账号）")
  private String userNo;
  /**
   * $column.comments
   */
  @ApiModelProperty(value = "登录ip")
  private String loginIp;
  /**
   * $column.comments
   */
  @ApiModelProperty(value = "登录时间")
  private LocalDateTime loginTime;

  @Override
  protected Serializable pkVal() {
    return null;
  }
}
