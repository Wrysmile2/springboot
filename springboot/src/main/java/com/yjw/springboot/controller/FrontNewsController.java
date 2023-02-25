package com.yjw.springboot.controller;

import com.yjw.springboot.common.Result;
import com.yjw.springboot.entity.NewsArticles;
import com.yjw.springboot.service.Impl.NewsArticlesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 向前端递送指定的新闻
 */
@RestController
@RequestMapping("/sendNews")
public class FrontNewsController {

    @Autowired
    private NewsArticlesServiceImpl newsArticlesService;

    /**
     * 向前端递送热点新闻
     * @return
     */
    @GetMapping("/hosSpot")
    public Result sendHotSpot(){
        List<NewsArticles> newsArticles = newsArticlesService.HotSpotNews();
        return Result.success(newsArticles);
    }

    /**
     * 向前端递送国内--社会新闻
     * @return
     */
    @GetMapping("/domesticNews")
    public Result sendDomestic(){
        List<NewsArticles> newsArticles = newsArticlesService.DomesticNews();
        return Result.success(newsArticles);
    }

    /**
     * 向前端递送国内--娱乐新闻
     * @return
     */
    @GetMapping("/entertainmentNews")
    public Result sendEntertainment(){
        List<NewsArticles> newsArticles = newsArticlesService.EntertainmentNews();
        return Result.success(newsArticles);
    }

    /**
     * 向前端递送国内--内地新闻
     * @return
     */
    @GetMapping("inlandNews")
    public Result sendInland(){
        List<NewsArticles> newsArticles = newsArticlesService.inlandNews();
        return null;
    }

}
