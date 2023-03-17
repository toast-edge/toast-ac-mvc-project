package com.toast.common.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe
 */
@Documented
@Target({ElementType.TYPE}) // 该注解需要在类型的定义上使用
@Retention(RetentionPolicy.RUNTIME) // 该注解在运行时生效
public @interface Aspect {}
