package com.yjw.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjw.springboot.entity.NewsArticles;

import java.util.List;

public interface INewsArticlesService extends IService<NewsArticles> {
    List<NewsArticles> HotSpotNews();

    List<NewsArticles> DomesticNews();

    List<NewsArticles> EntertainmentNews();

    List<NewsArticles> inlandNews();
}
