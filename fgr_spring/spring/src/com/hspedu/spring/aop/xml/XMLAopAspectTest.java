package com.hspedu.spring.aop.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class XMLAopAspectTest {

    @Test
    public void testAspectByXML(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans09.xml");

        SmartAnimal smartAnimal = ioc.getBean(SmartAnimal.class);
        Float sum = smartAnimal.getSum(1, 2);

        System.out.println(sum);
    }
}
