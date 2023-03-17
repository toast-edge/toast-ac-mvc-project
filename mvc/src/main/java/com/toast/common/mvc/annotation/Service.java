package com.toast.common.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {
    public String value() default "none"; // 设置具体的注解名称
}
