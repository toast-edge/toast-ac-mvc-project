package com.toast.common.mvc.annotation;

import jdk.jfr.Description;

import java.lang.annotation.*;

/**
 * @author 土司先生
 * @time 2023/1/16
 * @describe    控制层注解类
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    public String value() default "none";
}