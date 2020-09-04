package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.entity.ManageMenuMode;
import com.yjc.system.admin.entity.ManageRoleMenu;


import java.util.List;

/**
 * 菜单业务关联表
 *
 * @author yjc
 * @date 2020-07-13 15:37:07
 */
public interface ManageMenuModeService extends IService<ManageMenuMode> {

  /**
   * 菜单业务关联表简单分页查询
   * @param manageMenuMode 菜单业务关联表
   * @return
   */
  IPage<ManageMenuMode> getManageMenuModePage(Page<ManageMenuMode> page, ManageMenuMode manageMenuMode);


  List<ManageMenuMode> getmenuModeBymenu(List<ManageRoleMenu> list);
  List<Long> getmenuModeBymenuToModeCodeList(List<ManageRoleMenu> list);


}
