package com.example.userservice.dto;

import lombok.Data;

/**
 * @author hyosunghan
 * @since 2019-08-05
 */
@Data
public class LoginReq {

    private String username;

    private String password;

    private String telephone;

    private String validCode;

    private Integer landingMode;
}
