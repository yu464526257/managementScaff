package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户业务数据权限关联表
 *
 * @author yjc
 * @date 2020-07-05 18:58:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_USER_MODE_POWER")
public class ManageUserModePower extends BaseEntity {
private static final long serialVersionUID = 1L;

  /**
   * 用户id
   */
  @TableId
  @ApiModelProperty(value = "用户id")
  private String userId;
  /**
   * 用户登录编号
   */
  @ApiModelProperty(value = "用户编号")
  private Long userNo;
  /**
   * 业务编码
   */
  @ApiModelProperty(value = "业务编码")
  private Integer modeCode;
  /**
   * 业务名称
   */
  @ApiModelProperty(value = "业务名称")
  private String modeName;
  /**
   * 权限编号
   */
  @ApiModelProperty(value = "权限编号")
  private Integer powerCode;
  /**
   * 权限名称
   */
  @ApiModelProperty(value = "权限名称")
  private String powerName;
  /**
   * 权限内容
   */
  @ApiModelProperty(value = "权限内容")
  private String havaDatapower;

}
