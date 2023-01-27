package com.yjw.springboot.dto;

import lombok.Data;

/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDto {
    private String username;
    private String password;
}
