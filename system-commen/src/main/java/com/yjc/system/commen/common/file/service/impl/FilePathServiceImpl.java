package com.yjc.system.commen.common.file.service.impl;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/21
 * 所属功能
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.system.commen.common.file.dao.FilePathMapper;
import com.yjc.system.commen.common.file.entity.FilePath;
import com.yjc.system.commen.common.file.entity.FileResultEntity;
import com.yjc.system.commen.common.file.service.FilePathService;
import com.yjc.system.commen.common.utils.SpcFileUploadUtil;
import com.yjc.system.commen.common.utils.UuidUtil;
import com.yjc.system.commen.dto.base.ResultEntity;
import com.yjc.system.commen.common.file.dao.FilePathMapper;
import com.yjc.system.commen.common.file.entity.FilePath;
import com.yjc.system.commen.common.file.entity.FileResultEntity;
import com.yjc.system.commen.common.file.service.FilePathService;
import com.yjc.system.commen.common.utils.SpcFileUploadUtil;
import com.yjc.system.commen.common.utils.UuidUtil;
import com.yjc.system.commen.dto.base.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
public class FilePathServiceImpl extends ServiceImpl<FilePathMapper,FilePath> implements FilePathService {

    private final Logger log=LoggerFactory.getLogger(FilePathServiceImpl.class);

    @Autowired
    private FilePathMapper filePathMapper;

    @Override
    public ResultEntity<FilePath> save(HttpServletRequest request,
                                       MultipartFile file, FilePath filePath) {
        filePath.setId(UuidUtil.getUuidFor32());
        String fileNewName=(filePath.getId()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))).toLowerCase();
        filePath.setName(fileNewName);
        try {
            FileResultEntity fileResultEntity = new SpcFileUploadUtil().uploadFile(file, SpcFileUploadUtil.path, fileNewName);
            String fileUrl=null;
            if(fileResultEntity.getCode().equals("ok")){
                fileUrl=fileResultEntity.getMessage();
                filePath.setUrl(fileUrl);
            }else{
                return ResultEntity.failed("保存失败");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return ResultEntity.failed("保存失败");
        }

        try {
            if (filePathMapper.insert(filePath) > 0) {
                return ResultEntity.ok(filePathMapper.selectById(filePath.getId()));
            } else {
                return ResultEntity.failed("保存失败");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return ResultEntity.failed("保存失败");
        }
    }

    @Override
    public FilePath findUrlById(String id) {
        try {
            return filePathMapper.selectById(id);
        }catch (Exception e){
            log.error(e.toString());
            return null;
        }

    }


}
