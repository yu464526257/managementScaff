package com.yjc.system.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zyt
 * @date 2020/8/7 14:24
 * 角色菜单Dto
 */
@Data
public class RoleMenuDto {

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    private String roleId;
    /**
     * 菜单编号
     */
    @ApiModelProperty(value = "菜单编号")
    private Long menuId;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    /**
     * 父级菜单id
     */
    @ApiModelProperty(value = "父级菜单id")
    private Long menuParentId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Long positionIndex;
    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型")
    private Long menuType;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 菜单地址
     */
    @ApiModelProperty(value = "菜单地址")
    private String url;
    /**
     * 菜单数组
     */
    @ApiModelProperty(value = "菜单数组")
    private String menuArray;
    /**
     * 菜单数组  数组类型
     */
    private List<Integer> array;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 子级信息
     */
    @ApiModelProperty(value = "子级信息")
    private List<RoleMenuDto> child;
}
