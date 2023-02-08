package com.yjw.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
@TableName(value = "newstypes")
public class NewsTypes {
    @TableId(type = IdType.AUTO)
    private Integer typeId;
    @TableField(value = "type_name")
    private String typeName;
}
