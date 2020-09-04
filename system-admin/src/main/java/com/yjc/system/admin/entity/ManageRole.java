package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:46:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_ROLE")
public class ManageRole extends Model<ManageRole> {
private static final long serialVersionUID = 1L;

  /**
   * 角色ID
   */
  @TableId
  @ApiModelProperty(value = "角色ID")
  private String roleId;
  /**
   * 角色名称
   */
  @ApiModelProperty(value = "角色名称")
  private String roleName;
  /**
   * 角色权限字符串
   */
  @ApiModelProperty(value = "角色权限字符串")
  private String roleKey;
  /**
   * 角色状态:0正常,1禁用
   */
  @ApiModelProperty(value = "角色状态:0正常,1禁用")
  private Integer roleStatusCode;
  /**
   * 排序
   */
  @ApiModelProperty(value = "排序")
  private Integer positionIndex;
  /**
   * 角色等级
   */
  @ApiModelProperty(value = "角色等级")
  private Integer roleLevel;
  /**
   * 是否可编辑
   */
  @ApiModelProperty(value = "是否可编辑")
  private Integer isEdited;
  /**
   * 创建人
   */
  @ApiModelProperty(value = "创建人")
  private String creationUserId;
  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间")
  @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
  private LocalDateTime creationTime;
  /**
   * 修改时间
   */
  @ApiModelProperty(value = "修改时间")
  @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
  private LocalDateTime updateTime;
  /**
   * 修改人
   */
  @ApiModelProperty(value = "修改人")
  private String updateUserId;
  /**
   * 备注
   */
  @ApiModelProperty(value = "备注")
  private String remark;
  /**
   * 已删除：0否1是
   */
  @ApiModelProperty(value = "已删除：0否1是")
  private Integer isValid;

  @Override
  protected Serializable pkVal() {
    return this.roleId;
  }
}
