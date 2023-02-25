package com.yjw.springboot.dto;

import lombok.Data;

/**
 * 接受后台登陆时的请求参数
 */
@Data
public class UserInfoDto {
    private Integer id;
    private String username;
    private String password;
}
