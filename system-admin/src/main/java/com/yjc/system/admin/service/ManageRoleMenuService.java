package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.RoleMenuDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageRoleMenu;
import com.yjc.system.admin.entity.ManageUserRule;


import java.util.List;

/**
 * 角色跟老得岗位对应
 *
 * @author yjc
 * @date 2020-07-10 15:50:38
 */
public interface ManageRoleMenuService extends IService<ManageRoleMenu> {

  /**
   * 角色跟老得岗位对应简单分页查询
   * @param manageRoleMenu 角色跟老得岗位对应
   * @return
   */
  IPage<ManageRoleMenu> getManageRoleMenuPage(Page<ManageRoleMenu> page, ManageRoleMenu manageRoleMenu);

  //============================================================


  /**
   * 新增角色对应菜单关联表
   * @param manageRoleMenu  角色对应菜单关联表
   * @return
   */
  ResultEntity save(ManageRoleMenu manageRoleMenu);

  /**
   * 根据id修改
   * @param manageRoleMenu 角色对应菜单关联表
   * @return
   */
  ResultEntity modify(ManageRoleMenu manageRoleMenu);

  /**
   * 根据id删除
   * @param roleId  角色编号
   * @param menuId  菜单编号
   * @return
   */
  ResultEntity remove(String roleId, Long menuId);



  List<ManageRoleMenu> getRoleMenusByRole(List<ManageUserRule> list);

  /**
   * 根据角色id列表，查询用户信息
   * @param roles
   * @return
   */
  ResultEntity getMenuDtoByRoles(List<String> roles);

  /**
   * Tree树列表
   * @param roles
   * @return
   */
  List<RoleMenuDto> getList(List<String> roles);

  /**
   * 根据单个角色id，查询对应菜单
   * @param roleId
   * @return
   */
  List<RoleMenuDto> getMenuDtoByRoleId(String roleId);


}
