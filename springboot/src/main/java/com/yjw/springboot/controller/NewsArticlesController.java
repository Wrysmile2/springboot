package com.yjw.springboot.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjw.springboot.entity.NewsArticles;
import com.yjw.springboot.entity.User;
import com.yjw.springboot.service.Impl.NewsArticlesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsArticlesController {

    @Autowired
    private NewsArticlesServiceImpl newsArticlesService;

    /**
     * 读取新闻
     * @return
     */
    @GetMapping("/read")
    public List<NewsArticles> findAll() {
        List<NewsArticles> list = newsArticlesService.list();
        return list;
    }

    /**
     * 根据ID删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id){
        boolean result = newsArticlesService.removeById(id);
        return result;
    }

    /**
     * 批量删除新闻
     * @param ids
     * @return
     */
    @PostMapping("/delete/batch")
    public boolean deleteBatch(@RequestParam List<Integer> ids){
        boolean result = newsArticlesService.removeByIds(ids);
        return result;
    }

    /**
     * 文件导出
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<NewsArticles> list = newsArticlesService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);

        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    /**
     * 文件导入
     * @param file
     * @throws IOException
     */
    @PostMapping("/import")
    public void imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> users = reader.readAll(User.class);
        System.out.println(users);
    }

    /**
     * 分页查询--基于MyBatis-plus的方法
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public IPage<NewsArticles> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize){
        IPage<NewsArticles> page = new Page<>(pageNum, pageSize);
        QueryWrapper<NewsArticles> queryWrapper = new QueryWrapper<>();
        /*if (typeId != null) {
            queryWrapper.eq("typeId",typeId);
        }*/
        IPage<NewsArticles> newsArticlesIPage = newsArticlesService.page(page, queryWrapper);
        return newsArticlesIPage;
    }

}
