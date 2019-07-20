package com.example.userservice.dto;

import com.example.userservice.entity.User;

/**
 * Created by hysounghan on 2019/7/12.
 */
public class LoginDTO {
    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
