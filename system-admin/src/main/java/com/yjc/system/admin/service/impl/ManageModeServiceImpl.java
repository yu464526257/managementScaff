package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.system.admin.dao.ManageMenuModeMapper;
import com.yjc.system.admin.dao.ManageModeMapper;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageUser;
import com.yjc.system.commen.common.enums.TreeLevelEnum;
import com.yjc.system.commen.common.enums.WhetherEnum;
import com.yjc.system.commen.common.utils.CheckSpecial;
import com.yjc.system.commen.dto.base.TreeEntity;
import com.yjc.system.admin.entity.ManageDataPower;
import com.yjc.system.admin.entity.ManageMode;
import com.yjc.system.admin.entity.ManageUserRule;
import com.yjc.system.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 业务表
 *
 * @author yjc
 * @date 2020-07-13 15:51:36
 */
@Service("manageModeService")
public class ManageModeServiceImpl extends ServiceImpl<ManageModeMapper, ManageMode> implements ManageModeService {

    @Autowired
    private ManageUserRuleService manageUserRuleService;

    @Autowired
    private ManageRoleMenuService manageRoleMenuService;


    @Autowired
    private ManageMenuModeService manageMenuModeService;

    @Autowired
    private ManageDataPowerService manageDataPowerService;

    @Resource
    ManageModeMapper modeMapper;

    @Resource
    ManageMenuModeMapper menuModeMapper;

  /**
   * 业务表简单分页查询
   * @param manageMode 业务表
   * @return
   */
  @Override
  public IPage<ManageMode> getManageModePage(Page<ManageMode> page, ManageMode manageMode){
      return baseMapper.getManageModePage(page,manageMode);
  }

    @Override
    public List<ManageMode> getModeList(ManageUser manageUser) {
        List<ManageUserRule> list=manageUserRuleService.selectList(new QueryWrapper<ManageUserRule>().lambda().eq(ManageUserRule::getUserId,manageUser.getId()).eq(ManageUserRule::getUserNo,manageUser.getUserNo()));
        List<ManageRoleMenu> roleMenus=manageRoleMenuService.getRoleMenusByRole(list);
        if(!CheckSpecial.listCheck(roleMenus)){
            return  null;
        }
        List<Long> ModeIds=manageMenuModeService.getmenuModeBymenuToModeCodeList(roleMenus);
        List<ManageMode> modes=baseMapper.selectBatchIds(ModeIds);
        return modes;
    }

    @Override
    public  List<TreeEntity> getModePowerTree(ManageUser manageUser, Integer treeLevel) {
        List<TreeEntity> trees=new ArrayList<>();
        List<ManageUserRule> list=manageUserRuleService.selectList(new QueryWrapper<ManageUserRule>().lambda().eq(ManageUserRule::getUserId,manageUser.getId()).eq(ManageUserRule::getUserNo,manageUser.getUserNo()));
        List<ManageRoleMenu> roleMenus=manageRoleMenuService.getRoleMenusByRole(list);
        if(!CheckSpecial.listCheck(roleMenus)){
            return  null;
        }
        List<Long> ModeIds=manageMenuModeService.getmenuModeBymenuToModeCodeList(roleMenus);
        List<ManageMode> modes=baseMapper.selectBatchIds(ModeIds);
        if(TreeLevelEnum.FIRST.getCode().equals(treeLevel)){
            Optional.ofNullable(modes).ifPresent(models->{
                models.forEach(mode->{
                    TreeEntity tree=new TreeEntity();
                    tree.setCode(String.valueOf(mode.getModeCode()));
                    tree.setName(mode.getModeName());
                    tree.setIndex(TreeLevelEnum.FIRST.getCode());
                    tree.setChecked(WhetherEnum.NO.getCode());
                    trees.add(tree);
                });
            });
        } else if(TreeLevelEnum.SECOND.getCode().equals(treeLevel)){
            List<ManageDataPower> powers=manageDataPowerService.selectListByModeList(modes);
            Optional.ofNullable(powers).ifPresent(powerls->{
                powerls.forEach(prower->{
                    TreeEntity tree=new TreeEntity();
                    tree.setCode(String.valueOf(prower.getPowerCode()));
                    tree.setName(prower.getPowerName());
                    tree.setIndex(TreeLevelEnum.SECOND.getCode());
                    tree.setChecked(WhetherEnum.NO.getCode());
                    trees.add(tree);
                });
            });
        }
        return trees;
    }

    /**
     * 获取业务关联菜单列表
     * @param menuId
     * @return
     */
    @Override
    public ResultEntity getMenuList(String menuId) {
        ResultEntity resultEntity = new ResultEntity();
        List list = menuModeMapper.getMenuList(menuId);
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
     * 获取业务列表
     * @return
     */
    @Override
    public ResultEntity getModeList() {
        ResultEntity resultEntity = new ResultEntity();

        List list = modeMapper.getModeList();
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
     * 新增业务
     * @param name
     * @param code
     * @return
     */
    @Override
    public ResultEntity addManageMode(String name, int code) {
        ResultEntity resultEntity = new ResultEntity();
        ManageMode manageMode = new ManageMode();
        manageMode.setId(UUID.randomUUID().toString());
        manageMode.setModeName(name);
        manageMode.setModeCode(code);
        manageMode.setIsValid(0);
        manageMode.setCreationUserId("");
        manageMode.setUpdateUserId("");
        int i = modeMapper.insert(manageMode);

        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the insert is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the insert is failed");
        }
        return resultEntity;
    }

    /**
     * 删除业务
     * @param id
     * @return
     */
    @Override
    public ResultEntity delManageMode(String id) {
        ResultEntity resultEntity = new ResultEntity();
        ManageMode manageMode = new ManageMode();
        manageMode.setId(id);
        manageMode.setIsValid(1);
        int i = modeMapper.updateById(manageMode);

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
     * 修改业务
     * @param manageMode
     * @return
     */
    @Override
    public ResultEntity editManageMode(ManageMode manageMode) {
        ResultEntity resultEntity = new ResultEntity();
        int i = modeMapper.updateById(manageMode);
        if (i > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the update is success");
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the update is failed");
        }

        return resultEntity;
    }

}
