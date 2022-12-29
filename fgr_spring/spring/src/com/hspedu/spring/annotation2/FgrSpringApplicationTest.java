package com.hspedu.spring.annotation2;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class FgrSpringApplicationTest {
    public static void main(String[] args) {
        FgrSpringApplicationContext1 ioc = new FgrSpringApplicationContext1(FgrSpringConfig1.class);

        Object userAction = ioc.getBean("userAction");
        System.out.println(userAction);

        Object fgr1 = ioc.getBean("fgr1");
        System.out.println(fgr1);
    }
}
