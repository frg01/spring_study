package com.hspedu.spring.aop.aspectj;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class AopAspectTest {


    @Test
    public  void aroundSmartDogTestByProxy() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans08.xml");

        //通过接口类型  来获取到注入的SmartDog对象
        SmartAnimal smartAnimal = ioc.getBean(SmartAnimal.class);

        smartAnimal.getSum(1, 2);
        System.out.println("==========");
        smartAnimal.getSub(1,2);
//        System.out.println(smartAnimal.getClass());
    }

    @Test
    public  void smartDogTestByProxy() {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans08.xml");

        //通过接口类型  来获取到注入的SmartDog对象
        SmartAnimal smartAnimal = ioc.getBean(SmartAnimal.class);

        smartAnimal.getSum(1, 2);
//        System.out.println("==========");
//        smartAnimal.getSub(1,2);
//        System.out.println(smartAnimal.getClass());
    }
}
