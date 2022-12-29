package com.hspedu.spring.annotation;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class FgrSpringApplicationTest {
    public static void main(String[] args) {
        FgrSpringApplicationContext ioc = new FgrSpringApplicationContext(FgrSpringConfig.class);//类对象

        Object userAction = ioc.getBean("userAction");
        Object myComponent = ioc.getBean("myComponent");
        Object userDao = ioc.getBean("userDao");
        Object userService = ioc.getBean("userService");
        Object fgr1 = ioc.getBean("fgr1");
        System.out.println("fgr1" + fgr1);
        System.out.println("ok" + userAction);
        System.out.println(myComponent + "\n" + userDao + "\n" +userService);
    }
}
