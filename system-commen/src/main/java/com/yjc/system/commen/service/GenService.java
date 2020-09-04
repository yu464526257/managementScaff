package com.yjc.system.commen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.system.commen.entity.GenConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * @author lm
 * @date 2019/4/29 11:09
 * @Description TODO
 **/
public interface GenService {
    /**
     * 生成代码
     *
     * @param tableNames 表名称
     * @return
     */
    byte[] genCode(GenConfigEntity tableNames);

    /**
     * 批量生成代码
     *
     * @param tableNames 表名称
     * @return
     */
    byte[] genCode(String[] tableNames);

    /**
     * 分页查询表
     * @param tableName 表名
     * @return
     */
    IPage<List<Map<String, Object>>> queryPage(Page page, String tableName);
}
