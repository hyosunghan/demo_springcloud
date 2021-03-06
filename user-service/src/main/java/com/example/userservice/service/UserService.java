package com.example.userservice.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.exception.CommonException;
import com.example.common.exception.ErrorCode;
import com.example.userservice.client.AuthServiceClient;
import com.example.userservice.dto.LandingModeEnum;
import com.example.userservice.dto.LoginDTO;
import com.example.userservice.dto.LoginReq;
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

        User findResult = this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (null != findResult) {
            throw new CommonException(ErrorCode.USER_ALREADY_EXITS);
        }

        String entryPassword= BPwdEncoderUtils.BCryptPassword(user.getPassword());
        user.setPassword(entryPassword);
        this.baseMapper.insert(user);

        User result = this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        userRoleMapper.insert(UserRole.builder().userId(result.getId()).roleId(1L).build());

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer deleteUser(String username) {
        User user = this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        if (null == user) {
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        userRoleMapper.delete(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, user.getId()));
        return this.baseMapper.delete(Wrappers.<User>lambdaQuery().eq(User::getId, user.getId()));
    }

    public LoginDTO login(LoginReq req) {
        LoginDTO loginDTO = new LoginDTO();
        Integer landingMode = req.getLandingMode();
        switch (LandingModeEnum.getInstance(landingMode)) {
            case LANDING_VERIFICATION:
                break;
            case LANDING_UPASSWORD:
            default:
                String username = req.getUsername();
                String password = req.getPassword();
                User user = this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
                if (null == user) {
                    throw new CommonException(ErrorCode.USER_NOT_FOUND);
                }
                if (!BPwdEncoderUtils.matches(password, user.getPassword())) {
                    throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
                }

                JWT jwt = authServiceClient.getToken("Basic dWFhLXNlcnZpY2U6MTIzNDU2", "password", username, password);
                if (null == jwt) {
                    throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
                }
                loginDTO.setUser(user);
                loginDTO.setToken("Bearer " + jwt.getAccess_token());
        }

        return loginDTO;
    }
}
