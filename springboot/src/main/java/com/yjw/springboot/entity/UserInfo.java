package com.yjw.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "userinfo")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String address;
}
