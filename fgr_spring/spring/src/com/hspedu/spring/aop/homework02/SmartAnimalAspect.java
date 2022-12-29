package com.hspedu.spring.aop.homework02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Aspect
@Component
public class SmartAnimalAspect {

    @Before(value = "execution(public void com.hspedu.spring.aop.homework02.Camera.work()) || execution(public void com.hspedu.spring.aop.homework02.Phone.work())")
    public void f1(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("方法执行开始-日志-方法名-" +
                signature.getName() );
    }

    //返回通知 此方法切入到目标对象完成之后的位置
    @AfterReturning(value = "execution(public void com.hspedu.spring.aop.homework02.Camera.work())|| execution(public void com.hspedu.spring.aop.homework02.Phone.work())")
    public void f2(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("方法执行正常结束-日志-方法名-"
                + signature.getName() );
    }

    //此方法切入到目标对象发生异常的catch()
    @AfterThrowing(value = "execution(public void com.hspedu.spring.aop.homework02.Camera.work())|| execution(public void com.hspedu.spring.aop.homework02.Phone.work())")
    public void f3(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("方法执行异常-日志-方法名-" + signature.getName() + "异常类型=" );

    }

    //最终通知 此方法切入到目标对象方法无论怎样都最后有
    @After(value = "execution(public void com.hspedu.spring.aop.homework02.Camera.work())|| execution(public void com.hspedu.spring.aop.homework02.Phone.work())")
    public void f4(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("调用最终结束-日志-方法名-" + signature.getName() );
    }

}
