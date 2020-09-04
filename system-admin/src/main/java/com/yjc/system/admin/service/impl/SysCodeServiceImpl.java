package com.yjc.system.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yjc.system.admin.dao.SysCodeMapper;
import com.yjc.system.admin.dto.base.ResultEntity;
import com.yjc.system.admin.entity.SysCode;
import com.yjc.system.admin.service.SysCodeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典表
 *
 * @author yjc
 * @date 2020-07-01 14:05:57
 */
@Service("sysCodeService")
public class SysCodeServiceImpl extends ServiceImpl<SysCodeMapper, SysCode> implements SysCodeService {

  /**
   * 字典表简单分页查询
   * @param sysCode 字典表
   * @return
   */
  @Override
  public IPage<SysCode> getSysCodePage(Page<SysCode> page, SysCode sysCode){
      return baseMapper.getSysCodePage(page,sysCode);
  }

    /**
     * 根据类型获取code/Name
     * @param sysCode
     * @return
     */
    @Override
    public ResultEntity codeListByType(SysCode sysCode) {
        List<SysCode> list=baseMapper.getSysCodePage(sysCode);
        if(list.size()>0){
            return ResultEntity.ok(list);
        }else {
            return ResultEntity.failed("没有该类型参数");
        }
    }

    @Override
    public List<SysCode> codeListByType(String type) {
        return baseMapper.selectList(new QueryWrapper<SysCode>().lambda().eq(SysCode::getType,type));
    }

    @Override
    public Map<Integer, String> codeMapByType(String type) {

        List<SysCode> list= baseMapper.selectList(new QueryWrapper<SysCode>().lambda().eq(SysCode::getType,type));
        return list.stream().collect(Collectors.toMap(SysCode::getCode,SysCode::getName));
    }

    @Override
    public Integer getSequenceIdNextVal() {
        return baseMapper.getSequenceIdNextVal();
    }

}
