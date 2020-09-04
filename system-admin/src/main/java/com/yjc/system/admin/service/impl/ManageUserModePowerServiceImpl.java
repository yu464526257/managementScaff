package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageUserModePowerMapper;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageUserModePower;
import com.yjc.system.admin.service.ManageUserModePowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户业务数据权限关联表
 *
 * @author yjc
 * @date 2020-07-05 18:58:42
 */
@Service("manageUserModePowerService")
public class ManageUserModePowerServiceImpl extends ServiceImpl<ManageUserModePowerMapper, ManageUserModePower> implements ManageUserModePowerService {


    @Resource
    ManageUserModePowerMapper userModePowerMapper;

    /**
     * 用户业务数据权限关联表简单分页查询
     *
     * @param manageUserModePower 用户业务数据权限关联表
     * @return
     */
    @Override
    public IPage<ManageUserModePower> getManageUserModePowerPage(Page<ManageUserModePower> page, ManageUserModePower manageUserModePower) {
        return baseMapper.getManageUserModePowerPage(page, manageUserModePower);
    }

    @Override
    public void delModePower(UpdateUserDto updateUserDto) {
        baseMapper.delModePower(updateUserDto);
    }

    /**
     * 查询用户相关业务
     * @param userId
     * @return
     */
    @Override
    public ResultEntity getManageModeUserList(String userId) {
        ResultEntity resultEntity = new ResultEntity();
        List list = userModePowerMapper.getManageModeUserList(userId);

        if (list.size() > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the query is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the query is failed");
        }
        resultEntity.setData(list);
        return resultEntity;
    }

    /**
     * 删除单条用户相关业务
     * @param modeCode
     * @param powerCode
     * @param userId
     * @return
     */
    @Override
    public ResultEntity delManageModeUser(int modeCode, int powerCode, String userId) {
        ResultEntity resultEntity = new ResultEntity();
        int i = userModePowerMapper.delManageModeUser(modeCode, powerCode, userId);
        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the update is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the update is failed");
        }
        return resultEntity;
    }

    /**
     * 删除用户相关所有业务
     * @param id
     * @return
     */
    @Override
    public ResultEntity delAllManageModeUser(String id) {
        ResultEntity resultEntity = new ResultEntity();
        ManageUserModePower manageUserModePower = new ManageUserModePower();
        manageUserModePower.setIsValid(1);
        manageUserModePower.setUserId(id);
        int i = userModePowerMapper.updateById(manageUserModePower);
        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the update is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the update is failed");
        }
        return resultEntity;
    }

    /**
     * 修改用户相关业务
     * @param userId
     * @param list
     * @return
     */
    @Override
    public ResultEntity updateUserMode(String userId, List<ManageUserModePower> list) {
        ResultEntity resultEntity = new ResultEntity();
        ManageUserModePower manageUserModePower = new ManageUserModePower();
        manageUserModePower.setUserId(userId);
        manageUserModePower.setIsValid(1);
        userModePowerMapper.updateById(manageUserModePower);
        int i = 0;
        for (ManageUserModePower userModePower : list) {
            userModePower.setIsValid(0);
            i = userModePowerMapper.insert(userModePower);
        }
        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the update is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the update is failed");
        }
        return resultEntity;
    }

    @Override
    public ResultEntity addUserMode(List<ManageUserModePower> list) {
        ResultEntity resultEntity = new ResultEntity();
        int i = 0;
        for (ManageUserModePower manageUserModePower : list) {
            i = userModePowerMapper.insert(manageUserModePower);
        }
        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the insert is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the insert is failed");
        }
        return resultEntity;
    }
}
