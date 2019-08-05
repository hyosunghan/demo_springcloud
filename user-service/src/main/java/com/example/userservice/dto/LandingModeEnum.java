package com.example.userservice.dto;

/**
 * @author hyosunghan
 * @since 2019-08-05
 */
public enum LandingModeEnum {
    LANDING_UPASSWORD(1, "用户名密码登陆"),
    LANDING_VERIFICATION(2, "验证码登录");

    private Integer type;
    private String des;

    private LandingModeEnum(Integer type, String des) {
        this.type = type;
        this.des = des;
    }

    public Integer getType() {
        return this.type;
    }

    public String getDes() {
        return this.des;
    }

    public static LandingModeEnum getInstance(Integer type) {
        LandingModeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            LandingModeEnum value = var1[var3];
            if (type.equals(value.getType())) {
                return value;
            }
        }
        return LANDING_UPASSWORD;
    }
}
