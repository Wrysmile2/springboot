package com.yjw.springboot.dto;

import lombok.Data;

/**
 * 接受前台登录时请求的参数
 */
@Data
public class UserDto {
    private String username;
    private String password;
    private String Token;
}
