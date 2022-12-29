package com.hspedu.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Component
public class SmartDog implements SmartAnimalable {

    public float getSum(float i, float j) {
        float res = i + j;
        System.out.println("SmartDog getSum=" + res);
        return res;
    }

    public float getSub(float i, float j) {
        float res = i - j;
        System.out.println("SmartDog getSub=" + res);
        return res;
    }
}
