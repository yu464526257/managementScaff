package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.dto.RoleMenuDto;
import com.yjc.system.admin.entity.ManageRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色跟老得岗位对应
 *
 * @author yjc
 * @date 2020-07-10 15:50:38
 */
@Mapper
public interface ManageRoleMenuMapper extends BaseMapper<ManageRoleMenu> {
  /**
    * 角色跟老得岗位对应简单分页查询
    * @param manageRoleMenu 角色跟老得岗位对应
    * @return
    */
  IPage<ManageRoleMenu> getManageRoleMenuPage(Page page, @Param("manageRoleMenu") ManageRoleMenu manageRoleMenu);

  /**
   * 根据id修改
   * @param manageRoleMenu 角色对应菜单关联表
   * @return
   */
  Integer modify(@Param("manageRoleMenu") ManageRoleMenu manageRoleMenu);

    /**
     * 根据id删除
     * @param roleId  角色编号
     * @param menuId  菜单编号
     * @return
     */
    Integer remove(@Param("roleId") String roleId, @Param("menuId") Long menuId);

  List<ManageRoleMenu> getRoleMenusByRole(@Param("roles") List<String> roles);

  /**
   * 根据角色id查询该角色下的菜单
   * @param roleId
   * @return
   */
  List<ManageRoleMenu> getRoleMenuList(@Param("roleId") String roleId);

  /**
   *根据角色id 查询该角色下的菜单
   * @param roles  角色集合
   * @return
   */
  List<RoleMenuDto> getMenuDtoByRoles(@Param("roles") List<String> roles);

  /**
   *根据角色id 查询该角色下的菜单
   * @param roleId  角色编号
   * @return
   */
  List<RoleMenuDto> getMenuDtoByRoleId(@Param("roleId") String roleId);

  /**
   * 根据menu
   * @param menuId
   * @return
   */
  List<RoleMenuDto> getRoleMenuListByMenuId(@Param("menuId") Long menuId);

  /**
   * 根据菜单编号修改角色菜单表的菜单信息
   * @param manageRoleMenu
   * @return
   */
  Integer updateRoleMenuByMenuId(@Param("manageRoleMenu") ManageRoleMenu manageRoleMenu);
}
