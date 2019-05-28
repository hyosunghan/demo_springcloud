package com.example.servicefeign.controller;

import com.example.servicefeign.feign.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping("")
    public String hiService() {
        return schedualServiceHi.sayHiFromClientOne();
    }

}