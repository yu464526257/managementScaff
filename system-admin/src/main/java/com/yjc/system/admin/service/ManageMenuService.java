package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.RoleMenuDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageMenu;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ManageMenuService extends IService<ManageMenu> {

  ResultEntity getMenuList();

  /**
   * 菜单树状菜单列表
   * @return
   */
  List<RoleMenuDto> getManageMenuTree();

  /**
   * 新增菜单
   * @param manageMenu
   * @return
   */
  ResultEntity addManageMenu(ManageMenu manageMenu, HttpServletRequest request);

  /**
   * 修改菜单
   * @param manageMenu
   * @return
   */
  ResultEntity updateManageMenu(ManageMenu manageMenu, HttpServletRequest request);

  /**
   * 删除菜单
   * @param menuId
   * @return
   */
  ResultEntity deleteManageMenu(Long menuId, HttpServletRequest request);



}
