package com.yjc.system.commen.common.file.service;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/21
 * 所属功能
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.system.commen.common.file.entity.FilePath;
import com.yjc.system.commen.dto.base.ResultEntity;
import com.yjc.system.commen.common.file.entity.FilePath;
import com.yjc.system.commen.dto.base.ResultEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FilePathService extends IService<FilePath> {

    ResultEntity<FilePath> save(HttpServletRequest request,
                                MultipartFile file, FilePath filePath);


    FilePath findUrlById(String id);
}
