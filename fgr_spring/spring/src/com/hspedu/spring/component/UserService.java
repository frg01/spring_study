package com.hspedu.spring.component;

import org.springframework.stereotype.Service;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 使用 @Service 标识该类是一个Service类
 */
@Service
public class UserService {

    public void hi(){
        System.out.println("UserService 的hi（）方法");
    }
}
