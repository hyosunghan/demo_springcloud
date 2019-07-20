package com.example.userservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by hyosunghan on 2019/7/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SysLog {

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
