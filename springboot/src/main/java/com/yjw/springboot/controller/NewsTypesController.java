package com.yjw.springboot.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjw.springboot.entity.NewsTypes;
import com.yjw.springboot.entity.User;
import com.yjw.springboot.service.Impl.NewsTypesServiceImpl;
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
@RequestMapping("/newsType")
public class NewsTypesController {

    @Autowired
    private NewsTypesServiceImpl newsTypesService;

    /**
     * 增加或删除
     * @param newsTypes
     * @return
     */
    @PostMapping
    public boolean save(@RequestBody NewsTypes newsTypes) {
        return newsTypesService.saveOrUpdate(newsTypes);
    }

    /**
     * 根据ID删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return newsTypesService.removeById(id);
    }

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    @PostMapping("/delete/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return newsTypesService.removeByIds(ids);
    }

    /**
     * 遍历数据
     * @return
     */
    @GetMapping("/read")
    public List<NewsTypes> findAll() {
        return newsTypesService.list();
    }

    //分页查询--基于MyBatis-plus的方法
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param typeName
     * @return
     */
    @GetMapping("/page")
    public IPage<NewsTypes> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String typeName) {
        IPage<NewsTypes> page = new Page<>(pageNum, pageSize);
        QueryWrapper<NewsTypes> queryWrapper = new QueryWrapper<>();
        if (!("".equals(typeName))) {
            queryWrapper.like("typeName", typeName);
        }
        IPage<NewsTypes> newsTypesIPage = newsTypesService.page(page, queryWrapper);
        return newsTypesIPage;
    }

    /**
     * 文件导出
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<NewsTypes> list = newsTypesService.list();
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

}
