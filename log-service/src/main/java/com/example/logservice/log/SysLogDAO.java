package com.example.logservice.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SysLogDAO extends JpaRepository<SysLog, Long> {
}
