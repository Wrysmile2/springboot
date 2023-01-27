package com.yjw.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjw.springboot.dto.UserDto;
import com.yjw.springboot.entity.User;
import com.yjw.springboot.service.Impl.UserServiceImpl;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public boolean login(@RequestBody UserDto userDto){
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return false;
        }
        return userService.login(userDto);
    }

    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    @PostMapping("/delete/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }

    /*@PostMapping("/update")
    public int update(@RequestBody User user) {
        return userService.updateUser(user);
    }*/

    @GetMapping("/read")
    public List<User> findAll() {
        return userService.list();
    }

    //分页查询--基于limit关键字手写分页
    //limit第一个参数 = (pageNum-1) * pageSize
   /* @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username) {
        pageNum = (pageNum - 1) * pageSize;
        username = "%" + username + "%";
        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
        Integer total = userMapper.selectTotal(username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }*/

    //分页查询--基于MyBatis-plus的方法
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!("".equals(username))) {
            queryWrapper.like("username", username);
        }
        IPage<User> userIPage = userService.page(page, queryWrapper);
        return userIPage;
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<User> list = userService.list();
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

    @PostMapping("/import")
    public void imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> users = reader.readAll(User.class);
        System.out.println(users);
    }

}













