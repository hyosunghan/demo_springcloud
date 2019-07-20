package com.example.uaaservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.uaaservice.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by hyosunghan on 2019/7/12.
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
