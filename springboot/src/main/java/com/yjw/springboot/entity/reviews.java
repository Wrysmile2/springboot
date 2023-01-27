package com.yjw.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class reviews {
    @TableId(type = IdType.AUTO)
    private int reviewId;
    private int userId;
    private int articleId;
    private String content;
    @TableField(value = "dateandtime")
    private String DateAndTime;
}
