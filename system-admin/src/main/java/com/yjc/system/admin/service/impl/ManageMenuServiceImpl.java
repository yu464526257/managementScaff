package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageMenuMapper;
import com.yjc.system.admin.dao.ManageModeMapper;
import com.yjc.system.admin.dao.ManageRoleMapper;
import com.yjc.system.admin.dao.ManageRoleMenuMapper;
import com.yjc.system.admin.dto.RoleMenuDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageMenu;
import com.yjc.system.admin.entity.ManageMode;
import com.yjc.system.admin.entity.ManageRoleMenu;
import com.yjc.system.admin.service.ManageMenuService;
import com.yjc.system.admin.service.ManageModeService;
import com.yjc.system.admin.service.RedisService;
import com.yjc.system.commen.common.enums.CommonConstants;
import com.yjc.system.commen.common.redis.RedisUtil;
import com.yjc.system.commen.common.utils.CookieUtil;
import com.yjc.system.commen.common.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service("manageMenuService")
public class ManageMenuServiceImpl extends ServiceImpl<ManageMenuMapper, ManageMenu> implements ManageMenuService {

    @Resource
    ManageMenuMapper manageMenuMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired(required = false)
    private ManageRoleMenuMapper manageRoleMenuMapper;
    @Autowired(required = false)
    private ManageModeMapper manageModeMapper;
    @Autowired
    private ManageModeService manageModeService;
    @Autowired
    private RedisService redisService;

    @Override
    public ResultEntity getMenuList() {
        ResultEntity resultEntity = new ResultEntity();
        List list = manageMenuMapper.getMenuList();
        if (list.size() > 0) {
            resultEntity.setMsg("the query is success");
            resultEntity.setCode(200);
        } else {
            resultEntity.setMsg("the query is failed");
            resultEntity.setCode(500);
        }
        resultEntity.setData(list);
        return resultEntity;
    }

    @Override
    public List<RoleMenuDto> getManageMenuTree() {
        List<RoleMenuDto> listNode = new ArrayList<RoleMenuDto>();
        List<RoleMenuDto> menuInfoList = manageMenuMapper.getMenuList();
        // 先找到所有的一级菜单
        for (int i = 0; i < menuInfoList.size(); i++) {
            if(menuInfoList.get(i).getMenuArray()!=""&&menuInfoList.get(i).getMenuArray()!=null){
                String bmArray = menuInfoList.get(i).getMenuArray();
                String replace = bmArray.replace("[", "").replace("]", "");
                String[] split = replace.split(",");
                List<Integer> array = new ArrayList<>();
                for (int j = 0; j < split.length; j++) {
                    if(!split[j].equals("")&&split[j]!=null){
                        array.add(Integer.parseInt(split[j]));
                    }
                }
                menuInfoList.get(i).setArray(array);
            }
            // 一级菜单父ID为0
            Long parentId = menuInfoList.get(i).getMenuParentId();
            if (parentId == 0) {
                listNode.add(menuInfoList.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (RoleMenuDto sysDepts : listNode) {
            if(sysDepts.getMenuArray()!=""&&sysDepts.getMenuArray()!=null) {
                String bmArray = sysDepts.getMenuArray();
                String replace = bmArray.replace("[", "").replace("]", "");
                String[] split = replace.split(",");
                List<Integer> array = new ArrayList<>();
                for (int j = 0; j < split.length; j++) {
                    if (!split[j].equals("") && split[j] != null) {
                        array.add(Integer.parseInt(split[j]));
                    }
                }
                sysDepts.setArray(array);
            }
            List<RoleMenuDto> childList = getChildList(sysDepts.getMenuId(), menuInfoList);
            sysDepts.setChild(childList);
        }
        return listNode;
    }

    /**
     * 新增菜单
     * @param manageMenu
     * @return
     */
    @Override
    public ResultEntity addManageMenu(ManageMenu manageMenu, HttpServletRequest request) {
        String token = CookieUtil.getUid(request, "token");
        if(manageMenu.getMenuParentId()==null||manageMenu.getMenuParentId()==0){
            manageMenu.setMenuParentId(Long.parseLong("0"));
        }else{
            manageMenu.setMenuParentId(manageMenu.getMenuParentId());
        }

        if(manageMenu.getArray()!=null){
            String menuArray=manageMenu.getArray().toString();
            manageMenu.setMenuArray(menuArray);
            if(manageMenu.getArray().get(0)==10086){
                manageMenu.setMenuParentId(Long.parseLong("0"));
            }
        }

        //生成menuId
        Long maxMenuId = manageMenuMapper.getMaxMenuId();
        manageMenu.setMenuId(maxMenuId+1);
        //获取用户编号
        String userId = redisUtil.getObject(token, String.class);
        manageMenu.setCreationUserId(userId);
        Integer integer = manageMenuMapper.addManageMenu(manageMenu);
        if (integer > 0) {
            //同时添加业务表信息
            ManageMode manageMode=new ManageMode();
            manageMode.setId(UuidUtil.getUuidFor32());
            manageMode.setModeCode(Integer.parseInt(UuidUtil.createRandom()));
            manageMode.setModeName(manageMenu.getMenuName());
            manageMode.setCreationUserId(userId);
            Integer integer1 = manageModeMapper.addManageMode(manageMode);
            if(integer1>0){
                return ResultEntity.ok(manageMenu.getMenuArray());
            }else{
                return ResultEntity.failed("the insert is failed");
            }
        } else {
            return ResultEntity.failed("the insert is failed");
        }

    }

    /**
     * 修改菜单
     * @param manageMenu
     * @return
     */
    @Override
    public ResultEntity updateManageMenu(ManageMenu manageMenu, HttpServletRequest request) {
        String token = CookieUtil.getUid(request, "token");
        Long menuId = manageMenu.getMenuId();
        //获取用户编号
        String userId = redisUtil.getObject(token, String.class);
        if(manageMenu.getArray()!=null){
            String menuArray=manageMenu.getArray().toString();
            manageMenu.setMenuArray(menuArray);
            if(manageMenu.getArray().get(0)==10086){
                manageMenu.setMenuParentId(Long.parseLong("0"));
            }
        }
        manageMenu.setUpdateUserId(userId);
        if (menuId != null) {
            //修改菜单表信息
            Integer integer = manageMenuMapper.updateManageMenu(manageMenu);
            if (integer > 0) {
                //修改业务表信息(待完善)
//                ManageMode manageMode=new ManageMode();
//                manageMode.setId();
//                manageMode.setModeName();
//                manageMode.setModeCode();
//                manageMode.setUpdateUserId(userId);
//
//                manageModeService.editManageMode(manageMode);

                //修改角色菜单表信息
                ManageRoleMenu manageRoleMenu=new ManageRoleMenu();
                manageRoleMenu.setMenuId(manageMenu.getMenuId());
                manageRoleMenu.setMenuName(manageMenu.getMenuName());
                manageRoleMenu.setUrl(manageMenu.getUrl());
                Integer integer1 = manageRoleMenuMapper.updateRoleMenuByMenuId(manageRoleMenu);
                if(integer1>0){
                    //
//                    boolean del = redisUtil.del(token);
//                    redisService.setUserDtoToRedis(token, userDto, CommonConstants.USER_REDIS_TIME_OUT);
                    boolean del = redisUtil.del(token);

                    return ResultEntity.ok(integer);
                }else{
                    return ResultEntity.failed("the update is failed");
                }
            } else {
                return ResultEntity.failed("the update is failed");
            }
        } else {
            return ResultEntity.failed("the update is failed");
        }
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @Override
    public ResultEntity deleteManageMenu(Long menuId, HttpServletRequest request) {
        String token = CookieUtil.getUid(request, "token");

        //获取用户编号
        String userId = redisUtil.getObject(token, String.class);
        if (menuId != null) {

            List<RoleMenuDto> roleMenuListByMenuId = manageRoleMenuMapper.getRoleMenuListByMenuId(menuId);
            if (roleMenuListByMenuId != null && roleMenuListByMenuId.size() > 0) {
                //先将该角色与菜单关联信息删除
                for (int i = 1; i < roleMenuListByMenuId.size(); i++) {
                    manageRoleMenuMapper.remove(roleMenuListByMenuId.get(i).getRoleId(),menuId);
                }
            }
            //再将业务表信息删除

            //删除菜单表
            Integer integer = manageMenuMapper.deleteManageMenu(userId,menuId);
            if (integer > 0) {
                return ResultEntity.ok(integer);
            } else {
                return ResultEntity.failed("the delete is failed");
            }
        } else {
            return ResultEntity.failed("the delete is failed");
        }
    }

    /**
     * 子菜单查询
     * @param id
     * @param sysDeptss
     * @return
     */
    public List<RoleMenuDto> getChildList(Long id,List<RoleMenuDto> sysDeptss) {
        // 子菜单
        List<RoleMenuDto> SysDeptsList = new ArrayList<>();
        for (RoleMenuDto sysDepts : sysDeptss) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (sysDepts.getMenuParentId().equals(id)) {
                SysDeptsList.add(sysDepts);
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (RoleMenuDto sysDepts : SysDeptsList) {
            sysDepts.setChild(getChildList(sysDepts.getMenuId(),sysDeptss));
        }

        // 判断递归结束
        if (SysDeptsList.size() == 0) {
            return null;
        }
        return SysDeptsList;
    }

}
