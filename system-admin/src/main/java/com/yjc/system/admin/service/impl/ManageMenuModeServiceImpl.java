package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageMenuModeMapper;
import com.yjc.system.admin.entity.ManageMenuMode;
import com.yjc.system.admin.entity.ManageRoleMenu;
import com.yjc.system.admin.service.ManageMenuModeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单业务关联表
 *
 * @author yjc
 * @date 2020-07-13 15:37:07
 */
@Service("manageMenuModeService")
public class ManageMenuModeServiceImpl extends ServiceImpl<ManageMenuModeMapper, ManageMenuMode> implements ManageMenuModeService {

  /**
   * 菜单业务关联表简单分页查询
   * @param manageMenuMode 菜单业务关联表
   * @return
   */
  @Override
  public IPage<ManageMenuMode> getManageMenuModePage(Page<ManageMenuMode> page, ManageMenuMode manageMenuMode){
      return baseMapper.getManageMenuModePage(page,manageMenuMode);
  }

    @Override
    public List<ManageMenuMode> getmenuModeBymenu(List<ManageRoleMenu> list) {
      List<Long> menuIds=list.stream().map(rm->rm.getMenuId()).collect(Collectors.toList());
      List<ManageMenuMode> modeIds=baseMapper.getmenuModeBymenu(menuIds);
        return modeIds;
    }

  @Override
  public List<Long> getmenuModeBymenuToModeCodeList(List<ManageRoleMenu> list) {
    List<Long> menuIds=list.stream().map(rm->rm.getMenuId()).collect(Collectors.toList());
    List<Long> modeIds=baseMapper.getmenuModeBymenuToModeCodeList(menuIds);
    return modeIds;
  }

}
