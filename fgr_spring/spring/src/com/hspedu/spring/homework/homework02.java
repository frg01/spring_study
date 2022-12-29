package com.hspedu.spring.homework;

import com.hspedu.spring.bean.Car;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class homework02 {
    @Test
    public void getBean(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");
        Car car01 = ioc.getBean("car01", Car.class);

        System.out.println(car01);
    }
}
