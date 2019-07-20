package com.example.blogservice.dto;

import com.example.blogservice.entity.Blog;
import com.example.blogservice.entity.User;

/**
 * Created by hyosunghan on 2019/7/12.
 */
public class BlogDetailDTO {
    private Blog blog;
    private User user;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
