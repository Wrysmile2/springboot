package com.yjw.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class NewsTypes {
    @TableId(type = IdType.AUTO)
    private int typeId;
    private String typeName;
}
