package com.yjw.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName(value = "newsarticles")
public class NewsArticles {
    private int articleId;
    private int typeId;
    private String title;
    @JsonIgnore
    private String content;
    @JsonIgnore
    private int userInfoId;
    @JsonIgnore
    private int hints;
    @JsonIgnore
    private String imagepath;
    private String DateAndTime;
}
