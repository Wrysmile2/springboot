package com.yjw.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjw.springboot.entity.UserInfo;
import com.yjw.springboot.mapper.UserInfoMapper;
import com.yjw.springboot.service.IUserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {



}
