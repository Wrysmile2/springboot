package com.yjw.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.yjw.springboot.common.Constants;
import com.yjw.springboot.common.Result;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.dto.UserInfoDto;
import com.yjw.springboot.service.Impl.UserInfoServiceImpl;
import com.yjw.springboot.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserInfoServiceImpl userInfoService;

    /**
     * 登录页
     * @param userDto
     * @return
     */
    @PostMapping("/front/login")
    public Result login(@RequestBody UserDto userDto){
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDto dto = userService.login(userDto);
        return Result.success(dto);
    }


    @PostMapping("/admin/login")
    public Result AdminLogin(@RequestBody UserInfoDto userInfoDto, HttpServletRequest request){
        String username = userInfoDto.getUsername();
        String password = userInfoDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserInfoDto userInfoDto1 = userInfoService.AdminLoginIF(userInfoDto);
        Integer userInfoId = userInfoService.selectId(username);
        userInfoDto1.setId(userInfoId);
        request.getSession().setAttribute("userInfo",userInfoDto1);
        return Result.success(userInfoDto1);
    }

}
