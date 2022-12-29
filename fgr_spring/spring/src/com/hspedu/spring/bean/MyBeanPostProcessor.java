package com.hspedu.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 后置处理器 需要实现BeanPostProcessor接口才能成为处理器
 */
public class MyBeanPostProcessor implements BeanPostProcessor {


    /**
     * 在执行某一个bean的init方法前被调用
     * @param bean ioc配置的bean传入
     * @param beanName 配置bean的id
     * @return Object 程序对传入的bean 进行修改和处理 后返回
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof House){
            ((House) bean).setName("小房子");
        }
        System.out.println("postProcessBeforeInitialization方法被调用  bean=" + bean + "beanName" + beanName);
        return bean;
    }

    /**
     * 在执行某一个bean的init方法后被调用
     * @param bean ioc配置的bean传入
     * @param beanName 配置bean的id
     * @return Object 程序对传入的bean 进行修改和处理 后返回
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization方法被调用  bean=" + bean);
        return bean;
    }
}
