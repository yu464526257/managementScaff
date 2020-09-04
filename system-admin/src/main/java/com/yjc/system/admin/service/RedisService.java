package com.yjc.system.admin.service;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/5
 * 所属功能 redis 得业务功能模块
 */


import com.yjc.system.admin.dto.UserDto;
import com.yjc.system.admin.entity.ManageUser;
import org.springframework.stereotype.Service;


@Service
public interface RedisService {




    public void setUserToRedis(String token, ManageUser user, Long tokeOutTime);
    public void setUserDtoToRedis(String token, UserDto user, Long tokeOutTime);

    public UserDto getUserByID(String id) ;

    public boolean setUserDto(UserDto userDto);

    public void delUserById(String id);
}
