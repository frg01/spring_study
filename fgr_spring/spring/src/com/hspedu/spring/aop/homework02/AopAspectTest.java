package com.hspedu.spring.aop.homework02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class AopAspectTest {
    public static void main(String[] args) {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");

        UsbInterface camera = (UsbInterface)ioc.getBean("camera");
        UsbInterface phone = (UsbInterface)ioc.getBean("phone");
        camera.work();
        phone.work();
        System.out.println("ok");

    }
}
