package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.system.admin.dao.ManageDataPowerMapper;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageDataPower;
import com.yjc.system.admin.entity.ManageMode;
import com.yjc.system.admin.service.ManageDataPowerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据权限表
 *
 * @author yjc
 * @date 2020-07-13 17:21:28
 */
@Service("manageDataPowerService")
public class ManageDataPowerServiceImpl extends ServiceImpl<ManageDataPowerMapper, ManageDataPower> implements ManageDataPowerService {



    /**
     * 数据权限表简单分页查询
     *
     * @param manageDataPower 数据权限表
     * @return
     */
    @Override
    public IPage<ManageDataPower> getManageDataPowerPage(Page<ManageDataPower> page, ManageDataPower manageDataPower) {
        return baseMapper.getManageDataPowerPage(page, manageDataPower);
    }
    /**
     * 根据业务代码集合查询数据权限
     * @param modes  业务编码
     * @return
     */
    @Override
    public List<ManageDataPower> selectListByModeList(List<ManageMode> modes) {
        List<Integer> modeCodes = modes.stream().map(mode -> mode.getModeCode()).collect(Collectors.toList());
        return baseMapper.selectListByModeList(modeCodes);
    }

    @Override
    public ResultEntity addManageDataPower(ManageDataPower manageDataPower) {
        ResultEntity resultEntity = new ResultEntity();
        int i = baseMapper.insert(manageDataPower);
        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the insert is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the insert is failed");
        }
        return resultEntity;
    }

    @Override
    public ResultEntity addAllManageDataPower(List<ManageDataPower> list) {
        ResultEntity resultEntity = new ResultEntity();
        int i = 0;
        for (ManageDataPower manageDataPower : list) {
            i = baseMapper.insert(manageDataPower);
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

    @Override
    public ResultEntity editManageDataPower(List<ManageDataPower> list) {
        ResultEntity resultEntity = new ResultEntity();
        int i = 0;
        for (ManageDataPower manageDataPower : list) {
            i = baseMapper.updateById(manageDataPower);
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
    public ResultEntity delManageDataPower(String id) {
        ResultEntity resultEntity = new ResultEntity();
        ManageDataPower dataPower = new ManageDataPower();
        dataPower.setId(id);
        int i = baseMapper.deleteById(dataPower);

        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the delete is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the delete is failed");
        }
        return resultEntity;
    }

    @Override
    public ResultEntity delUserManageDataPower(int modeCode) {
        ResultEntity resultEntity = new ResultEntity();
        int i = baseMapper.deleteByModeCode(modeCode);
        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the delete is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the delete is failed");
        }
        return resultEntity;
    }


}
