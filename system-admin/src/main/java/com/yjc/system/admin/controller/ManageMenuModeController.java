package com.yjc.system.admin.controller;

import com.yjc.system.admin.service.ManageMenuModeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 菜单业务关联表
 *
 * @author yjc
 * @date 2020-07-13 15:37:07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/managemenumode")
public class ManageMenuModeController {

  private final ManageMenuModeService manageMenuModeService;


}
