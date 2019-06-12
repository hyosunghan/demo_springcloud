package com.example.logservice.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogService {
    @Autowired
    SysLogDAO sysLogDAO;

    public void saveLogger(SysLog sysLog){
        sysLogDAO.save(sysLog);
    }
}
