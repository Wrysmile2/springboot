package com.yjw.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjw.springboot.entity.NewsArticles;
import com.yjw.springboot.entity.User;
import com.yjw.springboot.service.Impl.NewsArticlesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/add")
public class NewsArticlesController {

    @Autowired
    private NewsArticlesServiceImpl NewsArticlesService;

    @GetMapping("/read")
    public List<NewsArticles> findAll() {
        List<NewsArticles> list = NewsArticlesService.list();
        return list;
    }

    //分页查询--基于MyBatis-plus的方法
    @GetMapping("/page")
    public IPage<NewsArticles> findPage(@RequestParam Integer PageNum,
                                       @RequestParam Integer PageSize){
        Page<NewsArticles> page = new Page<>(PageNum, PageSize);
        QueryWrapper<NewsArticles> queryWrapper = new QueryWrapper<>();
        Page<NewsArticles> newsArticlesPage = NewsArticlesService.page(page, queryWrapper);
        return newsArticlesPage;
    }

}
