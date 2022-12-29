package com.hspedu.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 切面类
 */
public class SmartAnimalAspectj {

    public void f1(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("方法执行开始-日志-方法名-" +
                signature.getName() + "-参数 [" + Arrays.asList(joinPoint.getArgs()  ) + "]");
    }

    //返回通知 此方法切入到目标对象完成之后的位置
    public void f2(JoinPoint joinPoint,Object res){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("方法执行正常结束-日志-方法名-"
                + signature.getName() + "-结果 result=" +res);
    }

    //此方法切入到目标对象发生异常的catch()
    public void f3(JoinPoint joinPoint,Throwable throwable){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("方法执行异常-日志-方法名-" + signature.getName() + "异常类型=" + throwable );

    }

    //最终通知 此方法切入到目标对象方法无论怎样都最后有
    public void f4(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("方法最终完毕-日志-方法名-" + signature.getName());
    }
}
