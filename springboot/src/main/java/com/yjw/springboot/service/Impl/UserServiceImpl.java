package com.yjw.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.entity.User;
import com.yjw.springboot.mapper.UserMapper;
import com.yjw.springboot.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean login(UserDto userDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDto.getUsername());
        queryWrapper.eq("password",userDto.getPassword());
        User one = getOne(queryWrapper);
        return one != null;
    }
}
