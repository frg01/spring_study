package com.hspedu.spring.component;

import com.hspedu.spring.annotation.*;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 先当作切面类使用
 */
@Aspect
@Component
public class SmartAnimalAspect {
    @Before(value = "execution com.hspedu.spring.component.SmartDog getSum")
    public static void showBeginLog(){
        System.out.println("前置通知");
    }

    @AfterReturning(value = "execution com.hspedu.spring.component.SmartDog getSum")
    public static void showSuccessLog(){
        System.out.println("返回通知");
    }
}
