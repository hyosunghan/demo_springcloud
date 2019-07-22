package com.example.userservice.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.dto.RespDTO;
import com.example.common.exception.CommonException;
import com.example.common.exception.ErrorCode;
import com.example.userservice.client.AuthServiceClient;
import com.example.userservice.dto.LoginDTO;
import com.example.userservice.entity.JWT;
import com.example.userservice.entity.User;
import com.example.userservice.entity.UserRole;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.mapper.UserRoleMapper;
import com.example.userservice.util.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hyosunghan on 2019/7/12.
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    AuthServiceClient authServiceClient;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user) {
        this.baseMapper.insert(user);
        User result = this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        userRoleMapper.insert(UserRole.builder().userId(result.getId()).roleId(1L).build());
        return result;
    }

    public User getUserInfo(String username) {
        return this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }

    public RespDTO login(String username, String password) {
        User user = this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        if (null == user) {
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        if (!BPwdEncoderUtils.matches(password, user.getPassword())) {
            throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
        }

        JWT jwt = authServiceClient.getToken("Basic dWFhLXNlcnZpY2U6MTIzNDU2", "password", username, password);
        // 获得用户菜单
        if (null == jwt) {
            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
        }
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser(user);
        loginDTO.setToken(jwt.getAccess_token());
        return RespDTO.onSuc(loginDTO);
    }
}
