package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageRoleMapper;
import com.yjc.system.admin.dao.ManageRoleMenuMapper;
import com.yjc.system.admin.dto.ManageRoleDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageRole;
import com.yjc.system.admin.entity.ManageRoleMenu;
import com.yjc.system.admin.service.ManageRoleService;
import com.yjc.system.commen.common.enums.WhetherEnum;
import com.yjc.system.commen.common.redis.RedisUtil;
import com.yjc.system.commen.common.utils.CookieUtil;
import com.yjc.system.commen.common.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.util.List;

/**
 * ${comments}
 *
 * @author yjc
 * @date 2020-07-10 15:46:14
 */
@Service("manageRoleService")
public class ManageRoleServiceImpl extends ServiceImpl<ManageRoleMapper, ManageRole> implements ManageRoleService {
    @Autowired(required = false)
    private ManageRoleMapper manageRoleMapper;
    @Autowired(required = false)
    private ManageRoleMenuMapper manageRoleMenuMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public IPage<ManageRoleDto> getManageRolePage(Page<ManageRoleDto> page, ManageRoleDto manageRoleDto) {
        IPage<ManageRoleDto> iPagemanageRoleDto = baseMapper.getManageRolePage(page, manageRoleDto);
        List<ManageRoleDto> listOld = iPagemanageRoleDto.getRecords();
        listOld.forEach(dto -> {
            if (dto.getRoleStatusCode() == WhetherEnum.NO.getCode()) {
                dto.setRoleStatusCodeName(WhetherEnum.NO.getStrcode());
            } else if (dto.getRoleStatusCode() == WhetherEnum.YES.getCode()) {
                dto.setRoleStatusCodeName(WhetherEnum.YES.getStrcode());
            }
        });
        iPagemanageRoleDto.setRecords(listOld);
        return iPagemanageRoleDto;
    }

    /**
     * 查询全部角色
     *
     * @return
     */
    @Override
    public ResultEntity getManageRoleList() {
        List<ManageRole> manageRoleList = manageRoleMapper.getManageRoleList();
        return ResultEntity.ok(manageRoleList);
    }

    /**
     * 根据角色id查询角色
     *
     * @param roleId
     * @return
     */
    @Override
    public ResultEntity getManageRoleByRoleId(String roleId) {
        ResultEntity resultEntity = new ResultEntity();
        if (roleId != null && roleId != "") {
            ManageRole manageRoleById = manageRoleMapper.getManageRoleById(roleId);
            resultEntity.setCode(200);
            resultEntity.setMsg("查询成功");
            resultEntity.setData(manageRoleById);
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("参数为空");
        }
        return resultEntity;
    }

    /**
     * 保存
     *
     * @param manageRole
     * @return
     */
    @Override
    public ResultEntity save(ManageRole manageRole, HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                token = cookie.getValue();
            }
        }
//        String token = CookieUtil.getUid(request, "token");
        ResultEntity resultEntity = new ResultEntity();
        //角色id
        manageRole.setRoleId(UuidUtil.getUuidFor32());
        manageRole.setRoleStatusCode(WhetherEnum.NO.getCode());
        //获取用户编号
        //获取用户编号
        String userId = redisUtil.getObject(token, String.class);
        manageRole.setCreationUserId(userId);
        Integer integer = manageRoleMapper.addManageRole(manageRole);
        if (integer > 0) {
            resultEntity.setCode(200);
            resultEntity.setMsg("the insert is success");
            resultEntity.setData(integer);
        } else {
            resultEntity.setCode(500);
            resultEntity.setMsg("the insert is failed");
        }
        return resultEntity;
    }

    /**
     * 修改
     *
     * @param manageRole
     * @return
     */
    @Override
    public ResultEntity update(ManageRole manageRole, HttpServletRequest request) {

        String token = CookieUtil.getUid(request, "token");
        String roleId = manageRole.getRoleId();
        //获取用户编号
        String userId = redisUtil.getObject(token, String.class);
        manageRole.setUpdateUserId(userId);
        if (roleId != null && roleId != "") {
            Integer integer = manageRoleMapper.updateManageRole(manageRole);
            if (integer > 0) {
                return ResultEntity.ok(integer);
            } else {
                return ResultEntity.failed("the update is failed");
            }
        } else {
            return ResultEntity.failed("the update is failed");
        }
    }

    /**
     * 删除
     *
     * @param roleId 角色id
     * @return
     */
    @Override
    public ResultEntity delete(String roleId, HttpServletRequest request) {

        String token = CookieUtil.getUid(request, "token");
        List<ManageRoleMenu> roleMenuList = manageRoleMenuMapper.getRoleMenuList(roleId);
        if (roleMenuList != null && roleMenuList.size() > 0) {
            //先将该角色与菜单关联信息删除
            for (int i = 1; i < roleMenuList.size(); i++) {
                manageRoleMenuMapper.remove(roleId, roleMenuList.get(i).getMenuId());
            }
        }
        //删除角色
        //=================获取用户编号====================
        //获取用户编号
        String userId = redisUtil.getObject(token, String.class);
        String updateUserId = userId;
        Integer integer = manageRoleMapper.deleteManageRole(roleId, updateUserId);
        if (integer > 0) {
            return ResultEntity.ok(integer,"the delete is success");
        } else {
            return ResultEntity.failed("the delete is failed");
        }
    }
}
