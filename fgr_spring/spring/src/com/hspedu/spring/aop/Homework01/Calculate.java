package com.hspedu.spring.aop.Homework01;

import org.junit.jupiter.api.Test;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class Calculate {
    @Test
    public static void main(String[] args) {
        SmartAnimal smartDog = new SmartDog();
        SmartProxyProvider smartProxyProvider = new SmartProxyProvider(smartDog);

        SmartAnimal proxy = smartProxyProvider.getProxy();
        proxy.getSum(1,2);
        System.out.println("=====================");
        proxy.getSub(1,2);

    }
}
