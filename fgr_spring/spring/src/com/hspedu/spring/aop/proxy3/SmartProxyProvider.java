package com.hspedu.spring.aop.proxy3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 动态代理对象
 */
public class SmartProxyProvider {
    //定义要执行的目标的对象 实现了SmartAnimal
    private SmartAnimal target_smartAnimal;

    public SmartProxyProvider(SmartAnimal target_smartAnimal) {
        this.target_smartAnimal = target_smartAnimal;
    }

    public SmartAnimal getProxy(){
        /*
        ClassLoader loader,
        Class<?>[] interfaces,
        InvocationHandler h
         */

        //获取classLoader
        ClassLoader classLoader = target_smartAnimal.getClass().getClassLoader();
        //获取接口Class类
        Class<?>[] interfaces = target_smartAnimal.getClass().getInterfaces();
        //InvocationHandler 处理器
        InvocationHandler invocationHandler = new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    //横切关注点 前置通知
//                    System.out.println("方法执行开始-日志-方法名-" + name + "-参数 [" + Arrays.asList(args) + "]");
                    FgrAOP.before(proxy,method,args);
                    //反射以上与以下的日志都是横切关注点
                    result = method.invoke(target_smartAnimal, args);
                    //横切关注点 返回通知
                    FgrAOP.after(method,result);
//                    System.out.println("方法执行正常结束-日志-方法名-" + name + "-结果 result=" + result);
                } catch (Exception e) {
                    //如果反射执行方法时，出现异常进入catch 也是一个横切关注点 （异常通知）
                    System.out.println("方法执行异常-日志-方法名-" + method.getName() + "异常类型=" + e.getClass().getName() );
                } finally {//无论是否异常 都会finally
                    //横切关注点 最终通知
                    System.out.println("方法最终结束-日志-方法名-" + method.getName());
                }
                return result;
            }
        };
        SmartAnimal proxy = (SmartAnimal)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return proxy;
    }
}
