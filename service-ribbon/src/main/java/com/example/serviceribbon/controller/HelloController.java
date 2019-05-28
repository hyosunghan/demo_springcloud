package com.example.serviceribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    @HystrixCommand(fallbackMethod = "hiError") // 开启熔断器
    public String hiService() {
        return restTemplate.getForObject("http://EUREKA-CLIENT",String.class);
    }

    public String hiError() {
        return "hi,sorry,error!";
    }

}