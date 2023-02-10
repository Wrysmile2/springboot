package com.yjw.springboot.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjw.springboot.common.Constants;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.entity.UserInfo;
import com.yjw.springboot.exception.ServiceException;
import com.yjw.springboot.mapper.UserInfoMapper;
import com.yjw.springboot.service.IUserInfoService;
import com.yjw.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Override
    public UserDto AdminLogin(UserDto userDto) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        QueryWrapper<UserInfo> username = queryWrapper.eq("username", userDto.getUsername());
        queryWrapper.eq("password",userDto.getPassword());
        UserInfo one;
        try{
            one = getOne(queryWrapper);
        }catch (Exception e){
            log.info("异常情况:"+e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (one != null){
            BeanUtil.copyProperties(one,userDto,true);
            return userDto;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }
}
