package com.hspedu.spring.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 切面类 环绕通知
 */
//@Aspect//表示时一个切面类 （动态代理+反射+动态绑定）
//@Component//会注入SmartAnimalAspect到容器
public class SmartAnimalAspectj2 {
    //环绕通知

    //1.@Around 环绕通知  完成其他四个通知的功能
    //value 切入点表达式
    //doAround 表示切入方法 使用try-catch-finally结构
    @Around(value = "execution(public Float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float ,float ))")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object result = null;
        String methodName = joinPoint.getSignature().getName();

        try {

            Object[] args = joinPoint.getArgs();
            List<Object> objects = Arrays.asList(args);
            System.out.println("环绕通知:之前" + methodName);
            result = joinPoint.proceed();
            System.out.println("环绕通知:完成方法" + methodName + "结果" + result);

        } catch (Throwable throwable) {
            System.out.println("环绕通知:异常" + throwable);
        } finally {
            System.out.println("环绕通知:最终");
        }
        return result;
    }

}
