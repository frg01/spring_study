package com.hspedu.spring.processor;

import com.sun.istack.internal.Nullable;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 参考原生spring定义的一个接口 BeanPostProcessor
 * 该接口有两个方法 对象spring容器所有bean生效  是切面编程的概念
 */
public interface BeanPostProcessor {

    /**
     *postProcessBeforeInitialization 在bean的初始化方法之前使用
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName)  {
        return bean;
    }

    /**
     * postProcessAfterInitialization 在bean的初始化的方法之后使用
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessAfterInitialization(Object bean, String beanName)  {
        return bean;
    }
}
