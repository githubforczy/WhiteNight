package com.whitenight.gate.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WnLog {

    /**
     * 日志描述
     * @return
     */
    String value() default "";

    /**
     * 日志级别
     * @return
     */
    String level() default "";

}
