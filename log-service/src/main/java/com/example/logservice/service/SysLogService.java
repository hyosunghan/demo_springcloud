package com.example.logservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.logservice.entity.SysLog;
import com.example.logservice.mapper.SysLogMapper;
import org.springframework.stereotype.Service;

@Service
public class SysLogService extends ServiceImpl<SysLogMapper, SysLog> {

    public void saveLogger(SysLog sysLog){
        this.baseMapper.insert(sysLog);
    }
}
