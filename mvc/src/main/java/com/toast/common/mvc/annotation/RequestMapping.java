package com.toast.common.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author 土司先生
 * @time 2023/1/16
 * @describe
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // 该注解运行时生效
public @interface RequestMapping {
    /**
     * 路径名称
     * @return
     */
    public String value() default "/";
}
