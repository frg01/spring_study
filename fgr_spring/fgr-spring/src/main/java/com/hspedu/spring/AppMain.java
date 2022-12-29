package com.hspedu.spring;

import com.hspedu.spring.aop.SmartAnimalable;
import com.hspedu.spring.component.UserAction;
import com.hspedu.spring.component.UserDao;
import com.hspedu.spring.component.UserService;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class AppMain{
    public static void main(String[] args) {

        //测试容器中的bean以及依赖注入
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans01.xml");

        UserAction userAction = (UserAction)ioc.getBean("userAction");
        UserAction userAction2 = (UserAction)ioc.getBean("userAction");

        System.out.println("userAction=" + userAction);
        System.out.println("userAction2=" + userAction2);

        UserDao userDao = (UserDao)ioc.getBean("userDao");
        System.out.println("userDao= " + userDao);

        UserService userService = (UserService)ioc.getBean("userService");
        System.out.println("userService" + userService);

        userService.m1();

        SmartAnimalable smartAnimalable = ioc.getBean(SmartAnimalable.class);

        smartAnimalable.getSum(1,5);
    }
}
