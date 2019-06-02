package com.ajin.concurrency.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标识[不推荐]的类或写法
 *
 * @author ajin
 */
// 作用在类上
@Target(ElementType.TYPE)
// 编译后被忽略
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {

    String value() default "";
}
