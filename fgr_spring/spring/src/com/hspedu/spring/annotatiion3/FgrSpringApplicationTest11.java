package com.hspedu.spring.annotatiion3;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class FgrSpringApplicationTest11 {
    public static void main(String[] args) {
        ComponentApplicationContext2 ioc = new ComponentApplicationContext2(FgrSpringConfig2.class);

        Object userDao = ioc.getBean("userDao");
        System.out.println(userDao);

        Object fgr1 = ioc.getBean("fgr1");
        System.out.println(fgr1);
    }
}
