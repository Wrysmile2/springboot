package com.yjw.springboot.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.Session;
import com.yjw.springboot.common.Constants;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.dto.UserInfoDto;
import com.yjw.springboot.entity.UserInfo;
import com.yjw.springboot.exception.ServiceException;
import com.yjw.springboot.mapper.UserInfoMapper;
import com.yjw.springboot.service.IUserInfoService;
import com.yjw.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 判断登录条件
     * @param userInfoDto
     * @return
     */
    @Override
    public UserInfoDto AdminLoginIF(UserInfoDto userInfoDto) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        QueryWrapper<UserInfo> username = queryWrapper.eq("username", userInfoDto.getUsername());
        queryWrapper.eq("password",userInfoDto.getPassword());
        UserInfo one;
        try{
            one = getOne(queryWrapper);
        }catch (Exception e){
            log.info("异常情况:"+e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (one != null){
            BeanUtil.copyProperties(one,userInfoDto,true);
            return userInfoDto;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    /**
     * 根据管理员名字查询id
     * @param username
     * @return
     */
    @Override
    public Integer selectId(String username) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        QueryWrapper<UserInfo> userInfoID = queryWrapper.eq("username", username);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoID);
        Integer userId = userInfo.getUserId();
        return userId;
    }
}
