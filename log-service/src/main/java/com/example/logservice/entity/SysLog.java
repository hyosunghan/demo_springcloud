package com.example.logservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_log")
public class SysLog implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //用户名
    @TableField("username")
    private String username;
    //用户操作
    @TableField("operation")
    private String operation;
    //请求方法
    @TableField("method")
    private String method;
    //请求参数
    @TableField("params")
    private String params;
    //IP地址
    @TableField("ip")
    private String ip;
    //创建时间
    @TableField("create_date")
    private Date createDate;
}
