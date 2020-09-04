package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.entity.ManageDepartment;
import org.apache.ibatis.annotations.Param;

/**
 * 部门表
 *
 * @author yjc
 * @date 2020-07-05 17:31:36
 */
public interface ManageDepartmentMapper extends BaseMapper<ManageDepartment> {
  /**
    * 部门表简单分页查询
    * @param manageDepartment 部门表
    * @return
    */
  IPage<ManageDepartment> getManageDepartmentPage(Page page, @Param("manageDepartment") ManageDepartment manageDepartment);


}
