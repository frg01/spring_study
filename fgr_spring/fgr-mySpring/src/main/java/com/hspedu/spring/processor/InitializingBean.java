package com.hspedu.spring.processor;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 解读
 * 根据原生spring机制  定义一个接口 有一个方法
 * afterPropertiesSet这个方法 在Bean的setter后执行 即是我们原来的哪个初始化方法
 * 当一个Bean实现这个接口后  就实现afterPropertiesSet() 这个方法就是初始化方法
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
