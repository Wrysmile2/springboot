package com.yjw.springboot.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjw.springboot.common.Result;
import com.yjw.springboot.entity.User;
import com.yjw.springboot.entity.UserInfo;
import com.yjw.springboot.service.Impl.UserInfoServiceImpl;
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
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @GetMapping("/read")
    public List<UserInfo> findAll(){
        List<UserInfo> list = userInfoService.list();
        return list;
    }

    /**
     * 保存或更新
     * @param userInfo
     * @return
     */
    @PostMapping
    public boolean save(@RequestBody UserInfo userInfo) {
        return userInfoService.saveOrUpdate(userInfo);
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userInfoService.removeById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/delete/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userInfoService.removeByIds(ids);
    }

    /**
     * 个人信息
     * @param username
     * @return
     */
    /*@GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(userService.getOne(queryWrapper));
    }*/

    /**
     * 分页查询--基于MyBatis-plus的方法
     * @param pageNum
     * @param pageSize
     * @param username
     * @return
     */
    @GetMapping("/page")
    public IPage<UserInfo> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username) {
        IPage<UserInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        if (!("".equals(username))) {
            queryWrapper.like("username", username);
        }
        IPage<UserInfo> userIPage = userInfoService.page(page, queryWrapper);
        return userIPage;
    }

    /**
     * 数据导出
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<UserInfo> list = userInfoService.list();
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
     * 数据导入
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
