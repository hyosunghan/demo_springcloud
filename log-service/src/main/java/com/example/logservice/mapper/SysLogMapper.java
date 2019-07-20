package com.example.logservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.logservice.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {
}
