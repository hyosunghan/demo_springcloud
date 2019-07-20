package com.example.blogservice.client.hystrix;

import com.example.blogservice.client.UserServiceClient;
import com.example.blogservice.entity.User;
import com.example.common.dto.RespDTO;
import org.springframework.stereotype.Component;


/**
 * Created by hysounghan on 2019/7/12.
 */
@Component
public class UserServiceHystrix implements UserServiceClient {

    @Override
    public RespDTO<User> getUser(String token, String username) {
        System.out.println(token);
        System.out.println(username);
        return null;
    }
}
