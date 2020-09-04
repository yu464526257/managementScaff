package com.yjc.system.admin.service.impl;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/7/16
 * 所属功能
 */

import com.google.gson.Gson;
import com.yjc.system.admin.dto.UserDto;
import com.yjc.system.admin.entity.ManageUser;
import com.yjc.system.admin.service.RedisService;
import com.yjc.system.commen.common.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("RedisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void setUserToRedis(String token, ManageUser user, Long tokeOutTime) {

            Gson gson = new Gson();
            try {

                //用 token 保存用户id。
                redisUtil.setStrValue(token,user.getId());
                //并且设置超时时间
                if(redisUtil.hasKey(token)){
                    redisUtil.expire(token,tokeOutTime);
                }
                //根据用户id保存用户对象json字符串

            }catch (Exception e){
                log.error(e.toString());
                log.error("用户上传redis失败");
            }

    }

    @Override
    public void setUserDtoToRedis(String token, UserDto user, Long tokeOutTime) {

            Gson gson = new Gson();
            try {

                //用 token 保存用户id。
                boolean flag=redisUtil.setStrValue(token,user.getId());
                //并且设置超时时间
                if(redisUtil.hasKey(token)){
                    redisUtil.expire(token,tokeOutTime);
                }
                //根据用户id保存用户对象json字符串

            }catch (Exception e){
                log.error(e.getMessage());
                log.error("用户上传redis失败");
            }

    }

    @Override
    public UserDto getUserByID(String id) {
        {
            if(redisUtil.hasKey(id)){
                return redisUtil.getObject(id,UserDto.class);
            }
            return null;
        }
    }

    @Override
    public boolean setUserDto(UserDto userDto) {
            Gson gson = new Gson();

            String userJsonStr=gson.toJson(userDto);
            if(redisUtil.hasKey(userDto.getId())){
                redisUtil.del(userDto.getId());
            }
            redisUtil.setObjValue(userDto.getId(),userJsonStr);
            return true;
    }

    @Override
    public void delUserById(String id) {
        redisUtil.del(id);
    }
}
