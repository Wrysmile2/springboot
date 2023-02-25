package com.yjw.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.dto.UserInfoDto;
import com.yjw.springboot.entity.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {
    UserInfoDto AdminLoginIF(UserInfoDto userInfoDto);

    Integer selectId(String username);
}
