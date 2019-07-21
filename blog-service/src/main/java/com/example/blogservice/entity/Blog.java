package com.example.blogservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by hyosunghan on 2019/7/12.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("blog")
public class Blog implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("title")
    private String title;

    @TableField("suject")
    private String suject;
}
