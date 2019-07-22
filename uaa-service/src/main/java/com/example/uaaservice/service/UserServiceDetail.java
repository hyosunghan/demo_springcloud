package com.example.uaaservice.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.uaaservice.mapper.RoleMapper;
import com.example.uaaservice.mapper.UserMapper;
import com.example.uaaservice.mapper.UserRoleMapper;
import com.example.uaaservice.entity.Role;
import com.example.uaaservice.entity.User;
import com.example.uaaservice.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hyosunghan on 2019/7/12.
 */
@Service
public class UserServiceDetail extends ServiceImpl<UserMapper, User> implements UserDetailsService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));

        List<UserRole> userRoles = userRoleMapper.selectList(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, user.getId()));
        List<Long> userRoleIds = userRoles.stream().map(i -> i.getRoleId()).collect(Collectors.toList());
        List<Role> roles = roleMapper.selectBatchIds(userRoleIds);

        user.setAuthorities(roles);

        return user;
    }
}
