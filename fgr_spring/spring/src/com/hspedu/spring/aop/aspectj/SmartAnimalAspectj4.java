package com.hspedu.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 切面类
 */
@Order(value = 2)//表示该切面类执行顺序
@Aspect//表示时一个切面类 （动态代理+反射+动态绑定）
@Component//会注入SmartAnimalAspect到容器
public class SmartAnimalAspectj4 {

    //方法 希望将f1切入到SmartDag的getSum前执行 -前置通知

    /**
     * @Before 前置通知 目标对象执行方法前执行
     * @param joinPoint
     * value = "excution(public Float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float i, float j)
     * 切入到哪个类的哪个方法  形式 访问修饰符 返回类型 全类名.方法名(形参列表)
     * f1方法就是切入方法  可自己指定 一般为showBeginLog
     * joinPoint 在底层执行时  由AspectJ切面框架 会给该切入方法传入 joinPoint对象
     *  通过该方法，可以像相关信息
     */
    @Before(value = "execution(public Float com.hspedu.spring.aop.aspectj.SmartDog.*(float, float))")
    public void f1(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("SmartAnimalAspectj4方法执行开始-日志-方法名-" +
                signature.getName() + "-参数 [" + Arrays.asList(joinPoint.getArgs()  ) + "]");
    }

    //返回通知 此方法切入到目标对象完成之后的位置
    @AfterReturning(value = "execution(public Float com.hspedu.spring.aop.aspectj.SmartDog.*(float, float))",returning = "res")
    public void f2(JoinPoint joinPoint,Object res){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("SmartAnimalAspectj4方法执行正常结束-日志-方法名-"
                + signature.getName() + "-结果 result=" +res);
    }

    //此方法切入到目标对象发生异常的catch()
    @AfterThrowing(value = "execution(public Float com.hspedu.spring.aop.aspectj.SmartDog.*(float, float))",throwing = "throwable")
    public void f3(JoinPoint joinPoint,Throwable throwable){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("SmartAnimalAspectj4方法执行异常-日志-方法名-" + signature.getName() + "异常类型=" + throwable );

    }

    //最终通知 此方法切入到目标对象方法无论怎样都最后有
    @After(value = "execution(public Float com.hspedu.spring.aop.aspectj.SmartDog.getSum(float, float))")
    public void f4(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//通过连接点对象 可以获取方法签名
        System.out.println("SmartAnimalAspectj4方法最终完毕-日志-方法名-" + signature.getName());
    }
}
