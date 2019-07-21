package com.example.userservice.client.hystrix;


import com.example.userservice.client.AuthServiceClient;
import com.example.userservice.entity.JWT;
import org.springframework.stereotype.Component;

/**
 * Created by hysounghan on 2019/7/12.
 */
@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
