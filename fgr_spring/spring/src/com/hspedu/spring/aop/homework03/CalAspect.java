package com.hspedu.spring.aop.homework03;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * Cal切面
 */
@Aspect
@Component
public class CalAspect {

    //切入点表达式 在同一个包可简写
    @Pointcut(value = "execution(public Integer MyCal.*(int))")
    public void myPointCut(){
    }

    //创建时间的格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    //前置通知
    @Before(value = "myPointCut()")
    public void showBeginLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getName() + "开始执行计算  " + sdf.format(new Date()));
    }

    //返回通知
    @AfterReturning(value = "myPointCut()",returning = "res")
    public void showSuccessEndLog(JoinPoint joinPoint,Object res){
        System.out.println("结束执行计算  " + sdf.format(new Date()) + "  res=" +res);
    }

    //异常通知
    @AfterThrowing(value = "myPointCut()",throwing = "throwable")
    public void showExceptionLog(JoinPoint joinPoint,Throwable throwable){
        System.out.println("执行计算异常  " + throwable);
    }

    //最终通知
    @After(value = "myPointCut()")
    public void showFinallyLog(){
        System.out.println("最终结束。。。");
    }
}
