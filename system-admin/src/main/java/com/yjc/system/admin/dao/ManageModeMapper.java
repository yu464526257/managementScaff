package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.entity.ManageMode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务表
 *
 * @author yjc
 * @date 2020-07-13 15:51:36
 */
public interface ManageModeMapper extends BaseMapper<ManageMode> {
    /**
     * 业务表简单分页查询
     *
     * @param manageMode 业务表
     * @return
     */
    IPage<ManageMode> getManageModePage(Page page, @Param("manageMode") ManageMode manageMode);

    List getModeList();

    /**
     * 添加业务表信息
     * @param manageMode
     * @return
     */
    Integer addManageMode(@Param("manageMode") ManageMode manageMode);
}
