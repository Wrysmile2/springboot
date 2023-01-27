package com.yjw.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName(value = "userinfo")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String username;
    @JsonIgnore
    private String password;
    @TableField(value = "dateandtime")
    private String DateAndTime;
}
