package com.example.blogservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blogservice.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by hyosunghan on 2017/7/10.
 */

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
}
