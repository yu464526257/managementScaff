package com.yjc.system.commen.common.file.dao;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/21
 * 所属功能
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.system.commen.common.file.entity.FilePath;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FilePathMapper extends BaseMapper<FilePath> {

    /**
     * 新增
     * @param
     * @return
     */
    Integer insert(@Param("FilePath") FilePath filePath);}
