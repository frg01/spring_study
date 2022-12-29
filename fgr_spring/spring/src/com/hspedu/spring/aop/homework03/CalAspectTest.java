package com.hspedu.spring.aop.homework03;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class CalAspectTest {

    //通过注解或xml测试
    @Test
    public void testCalAspect(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans10.xml");
        Cal cal = ioc.getBean(Cal.class);
        cal.cal1(5);
        System.out.println("================");
        cal.cal2(5);

//        System.out.println(cal.getClass());
    }
}
