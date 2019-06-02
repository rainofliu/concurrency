package com.ajin.concurrency.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识一个类是线程安全的类
 *
 * @author ajin
 */
// 作用在类上
@Target(ElementType.TYPE)
// 编译后被忽略
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";


}
