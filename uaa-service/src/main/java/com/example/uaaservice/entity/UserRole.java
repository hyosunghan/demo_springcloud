package com.example.uaaservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author hyosunghan
 * @Description
 * @date 2019-07-20 11:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("user_role")
public class UserRole {

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;
}
