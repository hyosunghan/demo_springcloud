package com.example.blogservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blogservice.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by hyosunghan on 2017/7/10.
 */

@Mapper
@Repository
public interface BlogMapper extends BaseMapper<Blog> {
}
