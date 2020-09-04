package com.yjc.system.commen.service.impl;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.yjc.system.commen.common.utils.GenUtils;
import com.yjc.system.commen.dao.GenMapper;
import com.yjc.system.commen.entity.GenConfigEntity;
import com.yjc.system.commen.service.GenService;
import com.yjc.system.commen.common.utils.GenUtils;
import com.yjc.system.commen.entity.GenConfigEntity;
import com.yjc.system.commen.service.GenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器实现
 * @author lm
 * @date 2019/4/29 11:11
 * @Description TODO
 **/
@Service
@Slf4j
public class GenServiceImpl implements GenService {
    @Autowired
    private GenMapper genMapper;


    /**
     * 分页查询表
     *
     * @param tableName 查询条件
     * @return
     */
    @Override
    public IPage<List<Map<String, Object>>> queryPage(Page page, String tableName) {
        return genMapper.queryTableList(page,tableName);
    }

    /**
     * 生成代码
     *
     * @param genConfig 生成配置
     * @return
     */
    @Override
    public byte[] genCode(GenConfigEntity genConfig) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        //查询表信息
        Map<String, String> table = queryTableInfo(genConfig.getTableName());
        //查询列信息
        List<Map<String, String>> columns = queryColumns(genConfig.getTableName());
        //生成代码
        GenUtils.genCode(genConfig, table, columns, zip);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 生成配置
     * @return
     */
    @Override
    public byte[] genCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames){
            GenConfigEntity genConfig = new GenConfigEntity();
            genConfig.setTableName(tableName);
            //查询表信息
            Map<String, String> table = queryTableInfo(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.genCode(genConfig, table, columns, zip);
        }

        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    private Map<String, String> queryTableInfo(String tableName) {
        return genMapper.queryTableInfo(tableName);
    }

    private List<Map<String, String>> queryColumns(String tableName) {
        return genMapper.queryTableColumns(tableName);
    }
}
