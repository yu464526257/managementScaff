package com.yjc.system.admin.dto;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/10
 * 所属功能
 */

import com.yjc.system.admin.entity.ManageUser;
import com.yjc.system.admin.entity.ManageUserDept;
import com.yjc.system.admin.entity.ManageUserModePower;
import com.yjc.system.admin.entity.ManageUserRule;
import lombok.Data;

import java.util.List;

@Data
public class UserDto extends ManageUser {

   private List<ManageUserDept> userDepts;

   private List<ManageUserRule> userRoles;

   private List<RoleMenuDto> userMenus;

   private List<ManageUserModePower> powers;
}
