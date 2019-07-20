package com.example.blogservice.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blogservice.client.UserServiceClient;
import com.example.blogservice.dao.BlogMapper;
import com.example.blogservice.dto.BlogDetailDTO;
import com.example.blogservice.entity.Blog;
import com.example.blogservice.entity.User;
import com.example.blogservice.util.UserUtils;
import com.example.common.dto.RespDTO;
import com.example.common.exception.CommonException;
import com.example.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyosunghan on 2019/7/12.
 */
@Service
public class BlogService extends ServiceImpl<BlogMapper, Blog> {

    @Autowired
    UserServiceClient userServiceClient;

    public int postBlog(Blog blog) {
        return this.baseMapper.insert(blog);
    }

    public List<Blog> findBlogs(String username) {
        return this.baseMapper.selectList(Wrappers.<Blog>lambdaQuery().eq(Blog::getUsername, username));
    }

    public BlogDetailDTO findBlogDetail(Long id) {
        Blog blog = this.baseMapper.selectById(id);
        if (null == blog) {
            throw new CommonException(ErrorCode.BLOG_IS_NOT_EXIST);
        }
        RespDTO<User> respDTO = userServiceClient.getUser(UserUtils.getCurrentToken(), blog.getUsername());
        if (respDTO==null) {
            throw new CommonException(ErrorCode.RPC_ERROR);
        }
        BlogDetailDTO blogDetailDTO = new BlogDetailDTO();
        blogDetailDTO.setBlog(blog);
        blogDetailDTO.setUser(respDTO.data);
        return blogDetailDTO;
    }

}
