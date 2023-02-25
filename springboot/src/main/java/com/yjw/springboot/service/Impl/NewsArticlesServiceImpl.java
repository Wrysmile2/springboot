package com.yjw.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjw.springboot.entity.NewsArticles;
import com.yjw.springboot.mapper.NewsArticlesMapper;
import com.yjw.springboot.mapper.NewsTypesMapper;
import com.yjw.springboot.service.INewsArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsArticlesServiceImpl extends ServiceImpl<NewsArticlesMapper, NewsArticles> implements INewsArticlesService {

    @Autowired
    private NewsArticlesMapper newsArticlesMapper;

    /**
     * 查询热点新闻
     * @return
     */
    @Override
    public List<NewsArticles> HotSpotNews() {
        QueryWrapper<NewsArticles> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("hints").last("limit 5");
        List<NewsArticles> newsArticles = newsArticlesMapper.selectList(queryWrapper);
        return newsArticles;
    }

    /**
     * 查询国内--社会新闻
     * @return
     */
    @Override
    public List<NewsArticles> DomesticNews() {
        QueryWrapper<NewsArticles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id",3).last("limit 5");
        List<NewsArticles> newsArticles = newsArticlesMapper.selectList(queryWrapper);
        return newsArticles;
    }

    /**
     * 查询国内--娱乐新闻
     * @return
     */
    @Override
    public List<NewsArticles> EntertainmentNews() {
        QueryWrapper<NewsArticles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id",9).last("limit 5");
        List<NewsArticles> newsArticles = newsArticlesMapper.selectList(queryWrapper);
        return newsArticles;
    }

    /**
     * 查询国内--内地新闻
     * @return
     */
    @Override
    public List<NewsArticles> inlandNews() {

        return null;
    }
}
