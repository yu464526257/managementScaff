package com.yjc.system.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageRoleMapper;
import com.yjc.system.admin.dao.ManageUserMapper;
import com.yjc.system.admin.dao.ManageUserRuleMapper;
import com.yjc.system.admin.dto.*;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.*;
import com.yjc.system.admin.service.*;
import com.yjc.system.commen.common.config.BaseResourceConfig;
import com.yjc.system.commen.common.enums.CommonConstants;
import com.yjc.system.commen.common.enums.WhetherEnum;
import com.yjc.system.commen.common.redis.RedisUtil;
import com.yjc.system.commen.common.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.*;

/**
 * 用户表
 *
 * @author yjc
 * @date 2020-07-05 17:12:20
 */
@Slf4j
@Service("manageUserService")
public class ManageUserServiceImpl extends ServiceImpl<ManageUserMapper, ManageUser> implements ManageUserService {


    @Autowired
    private ManageUserModePowerService manageUserModePowerService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ManageDepartmentService manageDepartmentService;

    @Autowired
    private ManageUserDeptService manageUserDeptService;

    @Autowired
    private ManageUserRuleService manageUserRuleService;

    @Autowired
    private ManageRoleMenuService manageRoleMenuService;

    @Autowired
    private ManageDataPowerService manageDataPowerService;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired(required = false)
    private ManageUserMapper manageUserMapper;
    @Autowired(required = false)
    private ManageUserRuleMapper manageUserRuleMapper;

    @Autowired(required = false)
    private ManageRoleMapper manageRoleMapper;

    /**
     * 用户表简单分页查询
     *
     * @param manageUser 用户表
     * @return
     */
    @Override
    public IPage<ManageUser> getManageUserPage(Page<ManageUser> page, ManageUser manageUser) {
        return baseMapper.getManageUserPage(page, manageUser);
    }

    /**
     * 用户登录接口（第一版）
     *
     * @param user
     * @return
     */
    @Override
    public ResultEntity loginOld(Map<String, String> user) {
        ManageUser userObj = new ManageUser();
        if (StringUtils.isNotNull(user.get("USERNAME")) && "admin".equals(user.get("USERNAME"))) {
            userObj.setUserLoginNo(StringUtils.isNotNull(user.get("USERNAME")) ? user.get("USERNAME") : null);
            userObj = baseMapper.selectOne(new QueryWrapper<ManageUser>().lambda().eq(ManageUser::getUserLoginNo, user.get("USERNAME")));
        } else {
            userObj.setId(StringUtils.isNotNull(user.get("ROLEID")) ? user.get("ROLEID") : null);
            userObj = baseMapper.selectById(userObj.getId());
        }
        if (userObj != null) {
            userObj.setPowerList(manageUserModePowerService.selectList(new QueryWrapper<ManageUserModePower>().lambda().eq(ManageUserModePower::getUserId, userObj.getId()).eq(ManageUserModePower::getIsValid, WhetherEnum.NO.getCode())));
        } else {
            userObj = new ManageUser();
            if (user.get("USERNAME").equals("admin")) {
                userObj.setUserLoginNo("admin");
                userObj.setPassword(user.get("password"));
                userObj.setId(user.get("ID"));
                baseMapper.insert(userObj);
            } else {
                userObj.setId(StringUtils.isNotNull(user.get("ROLEID")) ? user.get("ROLEID") : null);
                userObj.setUserName(StringUtils.isNotNull(user.get("ROLENAME")) ? user.get("ROLENAME") : null);
                userObj.setPassword(StringUtils.isNotNull(user.get("password")) ? user.get("password") : null);
                userObj.setUserLoginNo(StringUtils.isNotNull(user.get("USERNAME")) ? user.get("USERNAME") : null);
                userObj.setRoleId(StringUtils.isNotNull(user.get("ROLEID")) ? user.get("ROLEID") : null);
                userObj.setDepartmentId(StringUtils.isNotNull(user.get("GWID")) ? user.get("GWID") : null);
                userObj.setPlatformId(StringUtils.isNotNull(user.get("PTID")) ? user.get("PTID") : null);
                baseMapper.saveUserOld(userObj);
            }
            //新生成用户

        }
        try {
            String token = userObj.getUserNo() + DateUtil.formatDate(new Date());
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            token = new String(Base64.getEncoder().encode(md5), "utf-8");
            redisService.setUserToRedis(token, userObj, CommonConstants.USER_REDIS_TIME_OUT);
            return ResultEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed("登录失败");
        }


    }

    /**
     * 用户登录接口（第二版）
     *
     * @param user
     * @param request
     * @param response
     * @return
     */
    @Override
    public ResultEntity login(LoginUserDto user, HttpServletRequest request, HttpServletResponse response) {
        try {

            ManageUser userObj = baseMapper.selectOne(new QueryWrapper<ManageUser>().lambda().eq(ManageUser::getUserLoginNo, user.getUserLoginNo()).eq(ManageUser::getPassword, user.getPassword()));
            UserDto userDto = null;
            if (userObj != null) {
                userDto = this.getUserById(userObj.getId());
                String token = AesUtils.encrypt(userObj.getUserNo() + DateUtil.formatDate(new Date()), BaseResourceConfig.encodeKey);
                redisService.setUserDtoToRedis(token, userDto, CommonConstants.USER_REDIS_TIME_OUT);
                Cookie cookie = new Cookie("token", token);
                //后加-------
                cookie.setPath("/");
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
                return ResultEntity.ok("登录成功");
            }
            return ResultEntity.failed("登录失败，账户密码错误");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultEntity.failed("登录异常，请联系管理员。");
        }


    }

    /**
     * 新增用户
     *
     * @param userDto
     * @return
     */
    @Override
    public ResultEntity addNewUser(NewAddUserDto userDto) {
        String id = UuidUtil.getUuidFor32();
        userDto.setId(id);
        ManageDepartment department = manageDepartmentService.selectById(userDto.getDepartmentId());
        try {
            baseMapper.addNewUser(userDto);
            UpdateUserDto updateUserDto = new UpdateUserDto();
            BeanCopierUtils.copy(userDto, updateUserDto);
            manageUserDeptService.inOrUpdateUserDept(updateUserDto);
            //创建一个用户和角色关联表对象
            ManageUserRule manageUserRule=new ManageUserRule();
            manageUserRule.setRoleId(userDto.getRoleId());
            //获取刚添加用户信息
            ManageUser manageUserById = manageUserMapper.getManageUserById(id);
            //获取角色信息
            ManageRole manageRoleById = manageRoleMapper.getManageRoleById(userDto.getRoleId());
            manageUserRule.setRoleName(manageRoleById.getRoleName());
            manageUserRule.setUserId(id);
            manageUserRule.setUserLoginNo(manageUserById.getUserLoginNo());
            manageUserRule.setUserNo(manageUserById.getUserNo());
            manageUserRule.setUserName(manageUserById.getUserName());
            manageUserRuleMapper.addUserRule(manageUserRule);
            return ResultEntity.ok("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed("用户保存失败");
        }
    }

    /**
     * 修改用户
     *
     * @param userDto
     * @return
     */
    @Override
    public ResultEntity updateUser(UpdateUserDto userDto) {
        if (!updateUserToInsertHis(userDto)) {
            return ResultEntity.failed("修改失败：历史记录保存失败。");
        }
        if (!updateUserToEditObj(userDto)) {
            return ResultEntity.failed("修改失败：修改账户信息失败");
        }
        if (!manageUserDeptService.inOrUpdateUserDept(userDto)) {
            return ResultEntity.failed("修改失败：修改用户关联部门失败");
        }
        redisService.delUserById(userDto.getId());
        return ResultEntity.ok("修改成功");
    }

    /**
     * 根据用户id获取用户
     *
     * @param id
     * @return
     */
    @Override
    public UserDto getUserById(String id) {
        try {

            ManageUser user = baseMapper.selectById(id);
            if (user != null) {
                UserDto userDto = redisService.getUserByID(id);
                if (userDto != null) {
                    return userDto;
                } else {
                    userDto = new UserDto();
                }
                BeanCopierUtils.copy(user, userDto);
                //get用户关联的部门
                List<ManageUserDept> userDepts = manageUserDeptService.selectList(new QueryWrapper<ManageUserDept>().lambda().eq(ManageUserDept::getUserId, user.getId()).eq(ManageUserDept::getUserNo, user.getUserNo()));
                if (CheckSpecial.listCheck(userDepts)) {
                    userDto.setUserDepts(userDepts);
                }
                //根据用户id获取用户角色
                List<ManageUserRule> userRoles = manageUserRuleService.selectList(new QueryWrapper<ManageUserRule>().lambda().eq(ManageUserRule::getUserId, user.getId()).eq(ManageUserRule::getUserNo, user.getUserNo()));
                List<String> roles = new ArrayList<>();
                if (CheckSpecial.listCheck(userRoles)) {
                    userDto.setUserRoles(userRoles);
                    userRoles.forEach(role -> {
                        roles.add(role.getRoleId());
                    });
                }
                //根据用户所有角色id获取manu列表
                List<RoleMenuDto> manuList = manageRoleMenuService.getList(roles);
//                //根据用户所有角色id获取manu列表
                if (CheckSpecial.listCheck(manuList)) {
                    userDto.setUserMenus(manuList);
                }



                List<ManageUserModePower> powerList = manageUserModePowerService.selectList(new QueryWrapper<ManageUserModePower>().lambda().eq(ManageUserModePower::getUserId, user.getId()));
                if (CheckSpecial.listCheck(powerList)) {
                    userDto.setPowers(powerList);
                }
                redisService.setUserDto(userDto);
                return userDto;

            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据token获取用户信息
     *
     * @return
     */
    @Override
    public UserDto getUserInfo(HttpServletRequest request) {
        try {

            String token=CookieUtil.getUid(request,"token");
            //获取用户编号
            String id = redisUtil.getObject(token, String.class);
            ManageUser user = baseMapper.selectById(id);
            if (user != null) {
                UserDto userDto = redisService.getUserByID(id);
                if (userDto != null) {
                    return userDto;
                } else {
                    userDto = new UserDto();
                }
                BeanCopierUtils.copy(user, userDto);
                //get用户关联的部门
                List<ManageUserDept> userDepts = manageUserDeptService.selectList(new QueryWrapper<ManageUserDept>().lambda().eq(ManageUserDept::getUserId, user.getId()).eq(ManageUserDept::getUserNo, user.getUserNo()));
                if (CheckSpecial.listCheck(userDepts)) {
                    userDto.setUserDepts(userDepts);
                }
                //根据用户id获取用户角色
                List<ManageUserRule> userRoles = manageUserRuleService.selectList(new QueryWrapper<ManageUserRule>().lambda().eq(ManageUserRule::getUserId, user.getId()).eq(ManageUserRule::getUserNo, user.getUserNo()));
                List<String> roles = new ArrayList<>();
                if (CheckSpecial.listCheck(userRoles)) {
                    userDto.setUserRoles(userRoles);
                    userRoles.forEach(role -> {
                        roles.add(role.getRoleId());
                    });
                }

                //根据用户所有角色id获取manu列表
                List<RoleMenuDto> manuList = manageRoleMenuService.getList(roles);
                //根据用户所有角色id获取manu列表
                if (CheckSpecial.listCheck(manuList)) {

                    userDto.setUserMenus(manuList);
                }

                List<ManageUserModePower> powerList = manageUserModePowerService.selectList(new QueryWrapper<ManageUserModePower>().lambda().eq(ManageUserModePower::getUserId, user.getId()));
                if (CheckSpecial.listCheck(powerList)) {
                    userDto.setPowers(powerList);
                }
                redisService.setUserDto(userDto);
                return userDto;

            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改用户密码
     *
     * @param updateUserDto
     * @return
     */
    @Override
    public ResultEntity updateUserPassword(UserChangePasswordDto updateUserDto) {
        try {
            ManageUser user = baseMapper.selectById(updateUserDto.getId());
            if (user != null) {
                UserDto userDto = redisService.getUserByID(user.getId());
                if (userDto != null) {
                    userDto.setPassword(updateUserDto.getNewPassword());
                    redisService.setUserDto(userDto);
                }
                user.setPassword(updateUserDto.getNewPassword());
                baseMapper.updateUserPassword(user);
                return ResultEntity.ok("密码修改成功");
            }
            return ResultEntity.failed("密码修改失败");

        } catch (Exception e) {
            return ResultEntity.failed("密码修改失败");
        }

    }

    @Override
    public ResultEntity updateUserRoles(UpdateUserDto userDto) {
        if (!manageUserRuleService.inOrUpdate(userDto)) {
            return ResultEntity.failed("保存失败");
        }
        return ResultEntity.ok("保存成功");
    }

    @Override
    public ResultEntity updateUserModel(UpdateUserDto userDto) {
        try {
            manageUserModePowerService.delModePower(userDto);
            List<ManageUserModePower> ls = userDto.getUserModePowers();
            Optional.ofNullable(ls).ifPresent(list -> {
                list.forEach(manageUserModePower -> {
                    manageUserModePower.setUserId(userDto.getId());
                    manageUserModePower.setUserNo(userDto.getUserNo());
                    ManageDataPower manageDataPower = manageDataPowerService.selectOne(new QueryWrapper<ManageDataPower>().lambda().eq(ManageDataPower::getByModeCode, manageUserModePower.getModeCode()).eq(ManageDataPower::getPowerCode, manageUserModePower.getPowerCode()));
                    manageUserModePower.setHavaDatapower(manageDataPower.getHavaDatapower());
                });
            });
            manageUserModePowerService.insertBatch(ls);
        } catch (Exception e) {
            return ResultEntity.failed("保存失败");
        }
        return ResultEntity.ok("保存成功");
    }

    /**
     * 查询全部用户信息
     * @return
     */
    @Override
    public ResultEntity getManageUser(Page page) {

        try {
            IPage<UserDto> userPage = manageUserMapper.getManageUser(page);
            if (userPage != null) {

               for(UserDto u : userPage.getRecords()){
                  //get用户关联的部门
                   List<ManageUserDept> userDepts = manageUserDeptService.selectList(new QueryWrapper<ManageUserDept>().lambda().eq(ManageUserDept::getUserId, u.getId()).eq(ManageUserDept::getUserNo, u.getUserNo()));
                   if (CheckSpecial.listCheck(userDepts)) {
                       u.setUserDepts(userDepts);
                   }
                   //根据用户id获取用户角色
                   List<ManageUserRule> userRoles = manageUserRuleService.selectList(new QueryWrapper<ManageUserRule>().lambda().eq(ManageUserRule::getUserId, u.getId()).eq(ManageUserRule::getUserNo, u.getUserNo()));
                   List<String> roles = new ArrayList<>();
                   if (CheckSpecial.listCheck(userRoles)) {
                       u.setUserRoles(userRoles);
                       userRoles.forEach(role -> {
                           roles.add(role.getRoleId());
                       });
                   }

                   //根据用户所有角色id获取manu列表
                   List<RoleMenuDto> manuList = manageRoleMenuService.getList(roles);
                   //根据用户所有角色id获取manu列表
                   if (CheckSpecial.listCheck(manuList)) {

                       u.setUserMenus(manuList);
                   }

                   List<ManageUserModePower> powerList = manageUserModePowerService.selectList(new QueryWrapper<ManageUserModePower>().lambda().eq(ManageUserModePower::getUserId, u.getId()));
                   if (CheckSpecial.listCheck(powerList)) {
                       u.setPowers(powerList);
                   }
               }
                return ResultEntity.ok(userPage);

            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private boolean updateUserToInsertHis(UpdateUserDto userDto) {
        userDto.setHisId(UuidUtil.getUuidFor32());
        try {
            baseMapper.setOldUserHis(userDto);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean updateUserToEditObj(UpdateUserDto userDto) {
        try {
            baseMapper.updateUser(userDto);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
