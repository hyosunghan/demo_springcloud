package com.example.servicefeign.feign;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne() {
        return "sorry,error. ";
    }
}