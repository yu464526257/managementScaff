package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 部门表
 *
 * @author yjc
 * @date 2020-07-05 17:31:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_DEPARTMENT")
public class ManageDepartment extends BaseEntity {
private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @TableId
  @ApiModelProperty(value = "id")
  private String id;
  /**
   * 部门名称
   */
  @ApiModelProperty(value = "部门名称")
  private String name;
  /**
   * 部门代码
   */
  @ApiModelProperty(value = "部门代码")
  private Long no;
  /**
   * 部门数据代码（原系统又，但不确定具体用途）
   */
  @ApiModelProperty(value = "部门数据代码（原系统又，但不确定具体用途）")
  private Long dataDm;
  /**
   * 这个就完全不知道具体用途
   */
  @ApiModelProperty(value = "这个就完全不知道具体用途")
  private Long px;
  /**
   * 创建人
   */
  @ApiModelProperty(value = "创建人")
  private String creationUserId;
  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间")
  private LocalDateTime creationTime;
  /**
   * 修改时间
   */
  @ApiModelProperty(value = "修改时间")
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
  /**
   * 部门级别
   */
  @ApiModelProperty(value = "部门级别")
  private Integer levels;
  /**
   * 平台id
   */
  @ApiModelProperty(value = "平台id")
  private String platformId;
  /**
   * 部门类型
   */
  @ApiModelProperty(value = "部门类型")
  private Integer type;

}
