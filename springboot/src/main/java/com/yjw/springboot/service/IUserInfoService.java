package com.yjw.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.entity.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {
    UserDto AdminLogin(UserDto userDto);
}
