package com.yjw.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjw.springboot.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
