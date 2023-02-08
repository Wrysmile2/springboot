package com.yjw.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjw.springboot.entity.NewsTypes;
import com.yjw.springboot.mapper.NewsTypesMapper;
import com.yjw.springboot.service.INewsTypesService;
import org.springframework.stereotype.Service;

@Service
public class NewsTypesServiceImpl extends ServiceImpl<NewsTypesMapper, NewsTypes> implements INewsTypesService {



}
