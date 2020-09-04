package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.entity.ManageDataPower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据权限表
 *
 * @author yjc
 * @date 2020-07-13 17:21:28
 */
public interface ManageDataPowerMapper extends BaseMapper<ManageDataPower> {
    /**
     * 数据权限表简单分页查询
     *
     * @param manageDataPower 数据权限表
     * @return
     */
    IPage<ManageDataPower> getManageDataPowerPage(Page page, @Param("manageDataPower") ManageDataPower manageDataPower);

    /**
     * 根据业务代码集合查询数据权限
     * @param powers  业务编码
     * @return
     */
    List<ManageDataPower> selectListByModeList(@Param("list") List<Integer> powers);

    /**
     * 根据所属业务代码进行删除
     * @param modeCode  所属业务编号
     * @return
     */
    int deleteByModeCode(int modeCode);
}
