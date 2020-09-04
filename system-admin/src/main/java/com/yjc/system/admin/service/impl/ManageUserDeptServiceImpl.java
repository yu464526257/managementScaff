package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageUserDeptMapper;
import com.yjc.system.admin.dto.UpdateUserDto;
import com.yjc.system.admin.entity.ManageDepartment;
import com.yjc.system.admin.entity.ManageUserDept;
import com.yjc.system.admin.service.ManageDepartmentService;
import com.yjc.system.admin.service.ManageUserDeptService;
import com.yjc.system.commen.common.enums.WhetherEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 用户部门关联表
 *
 * @author yjc
 * @date 2020-07-10 15:12:00
 */
@Service("manageUserDeptService")
public class ManageUserDeptServiceImpl extends ServiceImpl<ManageUserDeptMapper, ManageUserDept> implements ManageUserDeptService {


    @Autowired
    private ManageDepartmentService manageDepartmentService;
  /**
   * 用户部门关联表简单分页查询
   * @param manageUserDept 用户部门关联表
   * @return
   */
  @Override
  public IPage<ManageUserDept> getManageUserDeptPage(Page<ManageUserDept> page, ManageUserDept manageUserDept){
      return baseMapper.getManageUserDeptPage(page,manageUserDept);
  }

    @Override
    public boolean inOrUpdateUserDept(UpdateUserDto updateUserDto) {
      try {
          //删除原关联关系
          baseMapper.delete(new QueryWrapper<ManageUserDept>().lambda().eq(ManageUserDept::getUserId, updateUserDto.getId()).eq(ManageUserDept::getUserNo, updateUserDto.getUserNo()));
            //重新插入最新关联关系
          ManageUserDept majorDept=new ManageUserDept();

          ManageDepartment manageDepartment=manageDepartmentService.selectById(updateUserDto.getDepartmentId());
          if(manageDepartment != null){
              majorDept.setDeptName(manageDepartment.getName());
              majorDept.setDeptNo(manageDepartment.getNo());
              majorDept.setDeptId(manageDepartment.getId());
              majorDept.setUserId(updateUserDto.getId());
              majorDept.setUserNo(updateUserDto.getUserNo());
              majorDept.setIsMajor(WhetherEnum.YES.getDesc());
              majorDept.setId(updateUserDto.getDepartmentId());
              baseMapper.insert(majorDept);
          }

          List<String> secondaryDeptIds=Arrays.asList(updateUserDto.getSecondaryDepartmentIds().split(","));
          List<ManageDepartment> secondaryDepts=new ArrayList<ManageDepartment>(manageDepartmentService.selectBatchIds(secondaryDeptIds));
          Optional.ofNullable(secondaryDepts).ifPresent(list->{
              list.forEach(dept->{
                  majorDept.setDeptName(dept.getName());
                  majorDept.setDeptNo(dept.getNo());
                  majorDept.setDeptId(dept.getId());
                  majorDept.setUserId(updateUserDto.getId());
                  majorDept.setUserNo(updateUserDto.getUserNo());
                  majorDept.setIsMajor(WhetherEnum.NO.getDesc());
                  majorDept.setId(updateUserDto.getDepartmentId());
                  baseMapper.insert(majorDept);
              });
          });

      }catch ( Exception e){
          return false;
      }
        return true;
    }

}
