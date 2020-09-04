package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.entity.ManageUserModePower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户业务数据权限关联表
 *
 * @author yjc
 * @date 2020-07-05 18:58:42
 */
public interface ManageUserModePowerMapper extends BaseMapper<ManageUserModePower> {
    /**
     * 用户业务数据权限关联表简单分页查询
     *
     * @param manageUserModePower 用户业务数据权限关联表
     * @return
     */
    IPage<ManageUserModePower> getManageUserModePowerPage(Page page, @Param("manageUserModePower") ManageUserModePower manageUserModePower);

    void delModePower(@Param("user") UpdateUserDto updateUserDto);

    /**
     * 根据用户编号查询用户信息
     * @param userId
     * @return
     */
    List getManageModeUserList(@Param("userId") String userId);

    int delManageModeUser(int modeCode, int powerCode, String userId);


}
