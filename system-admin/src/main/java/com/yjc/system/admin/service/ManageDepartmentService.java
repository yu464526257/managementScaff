package com.yjc.system.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.admin.entity.ManageDepartment;

/**
 * 部门表
 *
 * @author yjc
 * @date 2020-07-05 17:31:36
 */
public interface ManageDepartmentService extends IService<ManageDepartment> {

  /**
   * 部门表简单分页查询
   * @param manageDepartment 部门表
   * @return
   */
  IPage<ManageDepartment> getManageDepartmentPage(Page<ManageDepartment> page, ManageDepartment manageDepartment);


}
