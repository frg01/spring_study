package com.hspedu.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public void m1(){
        userDao.hi();
    }

    //指定init(),是初始化方法
    @PostConstruct
    public void init() {
        System.out.println("UserService-init()");
    }
}
