package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.ManageDepartmentMapper;
import com.yjc.system.admin.entity.ManageDepartment;
import com.yjc.system.admin.service.ManageDepartmentService;
import org.springframework.stereotype.Service;

/**
 * 部门表
 *
 * @author yjc
 * @date 2020-07-05 17:31:36
 */
@Service("manageDepartmentService")
public class ManageDepartmentServiceImpl extends ServiceImpl<ManageDepartmentMapper, ManageDepartment> implements ManageDepartmentService {

  /**
   * 部门表简单分页查询
   * @param manageDepartment 部门表
   * @return
   */
  @Override
  public IPage<ManageDepartment> getManageDepartmentPage(Page<ManageDepartment> page, ManageDepartment manageDepartment){
      return baseMapper.getManageDepartmentPage(page,manageDepartment);
  }

}
