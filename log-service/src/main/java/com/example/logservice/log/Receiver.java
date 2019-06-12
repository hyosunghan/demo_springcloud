package com.example.logservice.log;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    SysLogService sysLogService;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        SysLog sysLog = JSON.parseObject(message, SysLog.class);
        sysLogService.saveLogger(sysLog);
        countDownLatch.countDown();
    }
}
