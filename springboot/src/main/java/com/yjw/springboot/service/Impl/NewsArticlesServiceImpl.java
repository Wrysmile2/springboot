package com.yjw.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjw.springboot.entity.NewsArticles;
import com.yjw.springboot.mapper.NewsArticlesMapper;
import com.yjw.springboot.service.INewsArticlesService;
import org.springframework.stereotype.Service;

@Service
public class NewsArticlesServiceImpl extends ServiceImpl<NewsArticlesMapper, NewsArticles> implements INewsArticlesService {



}
