package com.yjw.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjw.springboot.entity.NewsArticles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsArticlesMapper extends BaseMapper<NewsArticles> {
}
