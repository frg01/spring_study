package com.hspedu.spring2.processor;

/**
 * @author: guorui fu
 * @versiion: 1.0
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
