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
 * 切面 但使用xml去配置
 */
//@Aspect
//@Component
public class CalAspect2 {

    //创建时间的格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    //前置通知
    public void showBeginLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();

        System.out.println(signature.getName() +"开始执行计算  " + sdf.format(new Date()));
    }

    //返回通知
    public void showSuccessEndLog(JoinPoint joinPoint,Object res){
        System.out.println("结束执行计算  " + sdf.format(new Date()) + "  res=" +res);
    }

    //异常通知
    public void showExceptionLog(JoinPoint joinPoint,Throwable throwable){
        System.out.println("执行计算异常  " + throwable);
    }

    //最终通知
    public void showFinallyLog(){
        System.out.println("最终结束。。。");
    }
}
