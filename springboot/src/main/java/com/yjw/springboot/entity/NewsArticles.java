package com.yjw.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "newsarticles")
public class NewsArticles {
    @TableId(type = IdType.AUTO)
    private int articleId;
    private int typeId;
    private String title;
    private String content;
    private int userInfoId;
    @JsonIgnore
    private int hints;
    @JsonIgnore
    private String imagepath;
    private String DateAndTime;
}
