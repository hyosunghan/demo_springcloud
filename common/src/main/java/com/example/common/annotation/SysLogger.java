package com.example.common.annotation;

import java.lang.annotation.*;

/**
 * Created by hysounghan on 2019/7/12.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {
    String value() default "";
}
