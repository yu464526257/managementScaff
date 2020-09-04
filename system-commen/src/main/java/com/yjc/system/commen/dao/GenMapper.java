package com.yjc.system.commen.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * @author lm
 * @date 2019/4/29 11:04
 * @Description TODO
 **/
@Mapper
public interface GenMapper {

    /**
     * 分页查询表格
     *
     * @param page
     * @param tableName
     * @return
     */
    IPage<List<Map<String, Object>>> queryTableList(Page page, @Param("tableName") String tableName);

    /**
     * 查询表信息
     *
     * @param tableName 表名称
     * @return
     */
    Map<String, String> queryTableInfo(String tableName);

    /**
     * 查询表列信息
     *
     * @param tableName 表名称
     * @return
     */
    List<Map<String, String>> queryTableColumns(String tableName);
}
