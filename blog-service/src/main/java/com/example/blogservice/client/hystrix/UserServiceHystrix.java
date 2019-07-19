package com.example.blogservice.client.hystrix;

import com.forezp.client.UserServiceClient;
import com.forezp.dto.RespDTO;
import com.forezp.entity.User;
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
