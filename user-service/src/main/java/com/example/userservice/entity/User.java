package com.example.userservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by hysounghan on 2019/7/12.
 */

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("user")
public class User implements Serializable {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@NotNull
	@TableField("username")
	private String username;

	@TableField("password")
	private String password;
}
