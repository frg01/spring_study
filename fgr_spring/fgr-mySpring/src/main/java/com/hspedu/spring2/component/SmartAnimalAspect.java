package com.hspedu.spring2.component;

import com.hspedu.spring.annotation.AfterReturning;
import com.hspedu.spring.annotation.Before;
import com.hspedu.spring2.annotation.Component;
import com.hspedu.spring2.annotation.Aspect;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Aspect
@Component
public class SmartAnimalAspect {

    @Before(value = "execution com.hspedu.spring2.component.SmartDog getSum")
    public static void showBeginLog(){
        System.out.println("前置通知");
    }

    @AfterReturning(value = "execution com.hspedu.spring2.component.SmartDog getSum")
    public static void showSuccessLog(){
        System.out.println("返回通知");
    }

}
