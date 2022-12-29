package com.hspedu.spring.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 编写一个后置处理器
 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在Bean的init 初始化方法前调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization  被调用。。 beanName" + bean.getClass());
        return bean;
    }

    /**
     * bean的init 初始化方法后被调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization 被调用。。beanName" + bean.getClass());
        return bean;
    }
}
