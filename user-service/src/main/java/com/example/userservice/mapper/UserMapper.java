package com.example.userservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.userservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by hysounghan on 2019/7/12.
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
