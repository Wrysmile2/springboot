package com.yjw.springboot.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjw.springboot.common.Constants;
import com.yjw.springboot.common.Result;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.entity.User;
import com.yjw.springboot.exception.ServiceException;
import com.yjw.springboot.mapper.UserMapper;
import com.yjw.springboot.service.IUserService;
import com.yjw.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto login(UserDto userDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> username = queryWrapper.eq("username", userDto.getUsername());
        queryWrapper.eq("password", userDto.getPassword());
        User one;
        try{
            one = getOne(queryWrapper);
        }catch (Exception e){
            log.info("异常情况:"+e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (one != null){
            BeanUtil.copyProperties(one,userDto,true);
            // 设置token
            String token = TokenUtils.getToken(one.getId().toString(), one.getPassword());
            userDto.setToken(token);
            return userDto;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public User register(User user) {
        boolean userInfo = getUserInfo(user);
        if (userInfo == true){
            save(user);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名已存在");
        }
        return user;
    }

    private boolean getUserInfo(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String username = user.getUsername();
        queryWrapper.eq("username",username);
        User user1 = userMapper.selectOne(queryWrapper);
        log.info(String.valueOf(user1));
        if (user1 == null){
            return true;
        }else {
            return false;
        }
    }
}
