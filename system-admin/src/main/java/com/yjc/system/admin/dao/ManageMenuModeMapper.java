package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.entity.ManageMenuMode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单业务关联表
 *
 * @author yjc
 * @date 2020-07-13 15:37:07
 */
public interface ManageMenuModeMapper extends BaseMapper<ManageMenuMode> {
    /**
     * 菜单业务关联表简单分页查询
     *
     * @param manageMenuMode 菜单业务关联表
     * @return
     */
    IPage<ManageMenuMode> getManageMenuModePage(Page page, @Param("manageMenuMode") ManageMenuMode manageMenuMode);

    List<ManageMenuMode> getmenuModeBymenu(@Param("list") List<Long> list);

    List<Long> getmenuModeBymenuToModeCodeList(@Param("list") List<Long> list);

    List getMenuList(String menuId);
}
