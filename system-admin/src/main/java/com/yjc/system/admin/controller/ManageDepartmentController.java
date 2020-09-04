package com.yjc.system.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.admin.dto.ManageRoleDto;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.ManageDepartment;
import com.yjc.system.admin.service.ManageDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 部门表
 *
 * @author yjc
 * @date 2020-07-05 17:31:36
 */
@RestController
@AllArgsConstructor
@RequestMapping("/managedepartment")
@Api(tags="部门")
public class ManageDepartmentController {

  private final ManageDepartmentService manageDepartmentService;


  /**
   * 简单分页查询
   * @param page 分页对象
   * @param manageDepartment
   * @return
   */
  @PostMapping("/page")
  @ResponseBody
  @ApiOperation(value = "简单分页查询")
  public ResultEntity<IPage<ManageDepartment>> getStockPutInPage(Page<ManageDepartment> page, @RequestBody ManageDepartment manageDepartment) {
    return  ResultEntity.ok(manageDepartmentService.getManageDepartmentPage(page,manageDepartment));
  }



}
