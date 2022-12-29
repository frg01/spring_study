package com.hspedu.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.TYPE) 指定ComponentScan注解可以修饰的类型是类，接口，其他注解，枚举
//@Retention(RetentionPolicy.RUNTIME) 指定ComponentScan作用范围 运行时生效
//String value() default "";  ComponentScan可以传入一个value属性
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {
    String value() default "";//指定要扫描的包
}
