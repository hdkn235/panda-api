package com.hd.api.aspect;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截service
 * 
 * @author hoda
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {

	String description() default "";

}
