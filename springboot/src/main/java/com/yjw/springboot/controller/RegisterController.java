package com.yjw.springboot.controller;

import com.yjw.springboot.common.Result;
import com.yjw.springboot.entity.User;
import com.yjw.springboot.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        User register = userService.register(user);
        return Result.success(register);
    }

}
