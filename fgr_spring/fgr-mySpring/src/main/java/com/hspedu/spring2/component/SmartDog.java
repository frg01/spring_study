package com.hspedu.spring2.component;

import com.hspedu.spring2.annotation.Component;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Component
public class SmartDog implements SmartAnimal{

    @Override
    public float getSum(float i, float j) {
        float res = i + j;
        System.out.println("SmartDog - getSum-res=" + res);
        return res;
    }

    @Override
    public float getSub(float i, float j) {
        float res = i - j;
        System.out.println("SmartDog - getSub-res=" + res);
        return res;
    }
}
