package com.yjw.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjw.springboot.common.Result;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.entity.User;
import org.springframework.http.HttpRequest;


public interface IUserService extends IService<User> {
    UserDto login(UserDto userDto);

    User register(User user);
}
