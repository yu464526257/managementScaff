package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.dto.RoleMenuDto;
import com.yjc.system.admin.entity.ManageMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-13 14:39:52
 */
public interface ManageMenuMapper extends BaseMapper<ManageMenu> {
  /**
    * ${comments}简单分页查询
    * @param manageMenu ${comments}
    * @return
    */
  IPage<ManageMenu> getManageMenuPage(Page page, @Param("manageMenu") ManageMenu manageMenu);

  /**
   * 根据menuId查询菜单信息
   * @author zyt
   * @param menuId
   * @return
   */
  ManageMenu getManageMenuByMenuId(@Param("menuId") Long menuId);

  /**
   *查询全部菜单
   * @return
   */
  List<RoleMenuDto> getMenuList();

  /**
   * 新增菜单
   * @param manageMenu
   * @return
   */
  Integer addManageMenu(@Param("manageMenu") ManageMenu manageMenu);

  /**
   * 修改菜单
   * @param manageMenu
   * @return
   */
  Integer updateManageMenu(@Param("manageMenu") ManageMenu manageMenu);

  /**
   * 删除菜单
   * @param menuId
   * @return
   */
  Integer deleteManageMenu(@Param("updateUserId") String updateUserId,@Param("menuId") Long menuId);

  /**
   * 查询menuId的最大值
   * @return
   */
  Long getMaxMenuId();

}
