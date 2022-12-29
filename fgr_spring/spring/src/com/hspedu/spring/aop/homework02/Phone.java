package com.hspedu.spring.aop.homework02;

import org.springframework.stereotype.Component;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Component//注入到spring容器
public class Phone implements UsbInterface{
    @Override
    public void work() {
        System.out.println("Phone 的work()方法被调用。。");
    }
}
