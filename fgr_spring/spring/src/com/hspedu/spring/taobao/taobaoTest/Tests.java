package com.hspedu.spring.taobao.taobaoTest;

import com.hspedu.spring.taobao.service.MultiplyService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class Tests {

    @Test
    public void exchangeTest(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("taobao_ioc.xml");

        MultiplyService multiplyService = ioc.getBean(MultiplyService.class);
        multiplyService.ExchangeTx();
        System.out.println("ok");
    }
}
