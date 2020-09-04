package com.yjc.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.entity.ManageUserDept;
import org.apache.ibatis.annotations.Param;

/**
 * 用户部门关联表
 *
 * @author yjc
 * @date 2020-07-10 15:12:00
 */
public interface ManageUserDeptMapper extends BaseMapper<ManageUserDept> {
  /**
    * 用户部门关联表简单分页查询
    * @param manageUserDept 用户部门关联表
    * @return
    */
  IPage<ManageUserDept> getManageUserDeptPage(Page page, @Param("manageUserDept") ManageUserDept manageUserDept);


}
