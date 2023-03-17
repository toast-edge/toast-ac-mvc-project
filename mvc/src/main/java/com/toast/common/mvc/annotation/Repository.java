package com.toast.common.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
    public String value() default "none";
}
