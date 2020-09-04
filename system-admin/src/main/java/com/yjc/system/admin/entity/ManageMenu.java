package com.yjc.system.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-13 14:39:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("MANAGE_MENU")
public class ManageMenu extends BaseEntity {
private static final long serialVersionUID = 1L;

  /**
   * 菜单名称
   */
  @TableId
  @ApiModelProperty(value = "菜单名称")
  private String menuName;
  /**
   * 菜单ID
   */
  @ApiModelProperty(value = "菜单ID")
  private Long menuId;
  /**
   * 父菜单ID
   */
  @ApiModelProperty(value = "父菜单ID")
  private Long menuParentId;
  /**
   * 排序
   */
  @ApiModelProperty(value = "排序")
  private Integer positionIndex;
  /**
   * 请求地址
   */
  @ApiModelProperty(value = "请求地址")
  private String url;
  /**
   * 类型:0目录,1菜单,2按钮
   */
  @ApiModelProperty(value = "类型:0目录,1菜单,2按钮")
  private Integer menuType;
  /**
   * 菜单状态:0显示,1隐藏
   */
  @ApiModelProperty(value = "菜单状态:0显示,1隐藏")
  private Integer isVisible;
  /**
   * 权限标识
   */
  @ApiModelProperty(value = "权限标识")
  private String perms;
  /**
   * 菜单图标
   */
  @ApiModelProperty(value = "菜单图标")
  private String icon;
  /**
   * VUE界面
   */
  @ApiModelProperty(value = "VUE界面")
  private String component;
  /**
   * 路由缓存(是否开启路由缓存0否1是)
   */
  @ApiModelProperty(value = "路由缓存(是否开启路由缓存0否1是)")
  private Integer keepAlive;
  /**
   * 备注
   */
  @ApiModelProperty(value = "备注")
  private String remark;

  /**
   * 菜单数组
   */
  @ApiModelProperty(value = "菜单数组")
  private String menuArray;
  /**
   * 菜单数组  数组类型
   */
  private List<Integer> array;

}
