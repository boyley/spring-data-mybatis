package com.github.mybatis.annotations;

import java.lang.annotation.*;

/**
 * @author jarvis@caomeitu.com
 * @date 15/9/14
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MybatisQuery {
    String value();

    String query() default "";
}