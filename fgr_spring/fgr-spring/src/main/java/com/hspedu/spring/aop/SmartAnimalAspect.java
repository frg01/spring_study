package com.hspedu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author: guorui fu
 * @versiion: 1.0
 * 切面类
 */
@Aspect
@Component
public class SmartAnimalAspect {

    //配置前置，返回，异常，最终通知
    @Before(value = "execution(public float com.hspedu.spring.aop.SmartDog.getSum(float ,float))")
    public void showBeginLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的hi()-执行的目标方法-" + signature.getName());
    }

    //返回通知
    @AfterReturning(value = "execution(public float com.hspedu.spring.aop.SmartDog.getSum(float ,float))",returning = "res")
    public void showSuccessLog(JoinPoint joinPoint,Object res) {
        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok2()-执行的目标方法-" + signature.getName() + " res= " + res);

    }

    //异常通知
    @AfterThrowing(value = "execution(public float com.hspedu.spring.aop.SmartDog.getSum(float ,float ))",throwing = "throwable")
    public void ExceptionLog(JoinPoint joinPoint,Throwable throwable) {
        System.out.println("切面的ok3()-执行的异常-" + throwable );

    }

    //后置通知
    @After(value = "execution(public float com.hspedu.spring.aop.SmartDog.getSum(..))")
    public void showFinallyEndLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok4()-执行的目标方法-" + signature.getName());
    }
}
