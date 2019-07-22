package com.example.uaaservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

/**
 * Created by hyosunghan on 2019/7/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@TableName("role")
public class Role implements GrantedAuthority {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@NotNull
	@TableField("name")
	private String name;

	@Override
	public String getAuthority() {
		return name;
	}

}
