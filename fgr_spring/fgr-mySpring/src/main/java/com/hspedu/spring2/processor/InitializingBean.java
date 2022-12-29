package com.hspedu.spring2.processor;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 原生spring中的初始化方法
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
