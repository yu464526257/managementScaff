package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户部门关联表
 *
 * @author yjc
 * @date 2020-07-10 15:12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_USER_DEPT")
public class ManageUserDept extends Model<ManageUserDept> {
private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @TableId
  @ApiModelProperty(value = "id")
  private String id;
  /**
   * 用户编号
   */
  @ApiModelProperty(value = "用户编号")
  private Long userNo;
  /**
   * 用户id
   */
  @ApiModelProperty(value = "用户id")
  private String userId;
  /**
   * 部门id
   */
  @ApiModelProperty(value = "部门id")
  private String deptId;
  /**
   * 部门编码
   */
  @ApiModelProperty(value = "部门编码")
  private Long deptNo;
  /**
   * 部门名称
   */
  @ApiModelProperty(value = "部门名称")
  private String deptName;
  /**
   * 部门名称
   */
  @ApiModelProperty(value = "是否为主要部门，0否1是")
  private String isMajor;
  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
