package com.yjc.system.commen.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yjc.system.commen.dto.base.ResultEntity;
import com.yjc.system.commen.entity.GenConfigEntity;
import com.yjc.system.commen.service.GenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lm
 * @date 2019/4/29 11:32
 * @Description TODO
 **/
@Api(tags = "生成代码")
@RestController
@RequestMapping("/gen")
public class GenController {
    @Autowired
    private GenService genService;

    /**
     * 查看信息
     * @param page 分页参数
     * @param tableName 表名
     * @return
     */
    public ResultEntity<IPage> list(Page page, String tableName){
        return new ResultEntity <>(genService.queryPage(page,tableName));
    }
    /**
     * 生成代码
     */
    @PostMapping("/genCode")
    public void genCode(@RequestBody GenConfigEntity genConfig, HttpServletResponse response) throws IOException {
        byte[] data = genService.genCode(genConfig);
        response.reset();
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.zip", genConfig.getTableName()));
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }

    /**
     * 生成代码
     */
    @GetMapping("/genCode/{tableName}")
    public void genCodeGet(@PathVariable("tableName") String tableName, HttpServletResponse response) throws IOException {
        GenConfigEntity genConfig = new GenConfigEntity();
        genConfig.setTableName(tableName);
        byte[] data = genService.genCode(genConfig);
        response.reset();
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.zip", tableName));
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }



}
