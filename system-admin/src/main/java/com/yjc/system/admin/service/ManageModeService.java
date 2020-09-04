package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageMode;
import com.yjc.system.admin.entity.ManageUser;
import com.yjc.system.commen.dto.base.TreeEntity;


import java.util.List;

/**
 * 业务表
 *
 * @author yjc
 * @date 2020-07-13 15:51:36
 */
public interface ManageModeService extends IService<ManageMode> {

  /**
   * 业务表简单分页查询
   * @param manageMode 业务表
   * @return
   */
  IPage<ManageMode> getManageModePage(Page<ManageMode> page, ManageMode manageMode);


  /**
   * 查询菜单相关业务list
   */
    List<ManageMode> getModeList(ManageUser manageUser);
  /**
   * 查询菜单相关业务tree
   */
  List<TreeEntity> getModePowerTree(ManageUser manageUser, Integer treeLevel);

  /**
   * 获取业务关联菜单列表
   * @param menuId
   * @return
   */
  ResultEntity getMenuList(String menuId);

  /**
   * 获取业务列表
   * @return
   */
  ResultEntity getModeList();

  /**
   * 新增业务
   * @param name
   * @param code
   * @return
   */
  ResultEntity addManageMode(String name, int code);

  /**
   * 删除业务
   * @param id
   * @return
   */
  ResultEntity delManageMode(String id);

  /**
   * 修改业务
   * @param manageMode
   * @return
   */
  ResultEntity editManageMode(ManageMode manageMode);

}
