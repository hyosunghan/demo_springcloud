package com.example.userservice.web;

import com.example.common.annotation.SysLogger;
import com.example.common.dto.RespDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyosunghan on 2019/7/12.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "注册", notes = "username和password为必选项")
    @PostMapping("/registry")
    @SysLogger("registry")
    public RespDTO createUser(@RequestBody User user){
        return RespDTO.onSuc(userService.createUser(user));
    }

    @ApiOperation(value = "登录", notes = "username和password为必选项")
    @PostMapping("/login")
    @SysLogger("login")
    public RespDTO login(@RequestParam String username , @RequestParam String password){
      return RespDTO.onSuc(userService.login(username,password));
    }

    @ApiOperation(value = "根据用户名获取用户", notes = "根据用户名获取用户")
    @PostMapping("/{username}")
    @SysLogger("getUserInfo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RespDTO getUserInfo(@PathVariable("username") String username){
        return RespDTO.onSuc(userService.getUserInfo(username));
    }

}
