package com.hspedu.spring.aop.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * VehicleProxyProvider 该类可以返回一个代理对象

 */
public class VehicleProxyProvider {

    //target_vehicle 表示真正要执行的对象 该对象的类实现到了Vehicle接口
    private Vehicle target_vehicle;

    //构造器
    public VehicleProxyProvider(Vehicle target_vehicle) {
        this.target_vehicle = target_vehicle;
    }

    //编写一个方法  可以返回一个代理对象
    //Proxy.newProxyInstance 返回一个代理对象
    //ClassLoader loader  类的加载器
    //Class<?>[] interfaces 就是将来要代理对象的接口信息
    //InvocationHandler h  调用处理器/对象  有一个重要方法invoke

    public Vehicle getProxy(){

        //得到类加载器
        ClassLoader classLoader =
                target_vehicle.getClass().getClassLoader();
        //得到要代理的对象/被执行对象的接口信息 底层是通过接口完成调用
        Class<?>[] interfaces =
                target_vehicle.getClass().getInterfaces();
        //调用处理器 InvocationHandler是接口  可以通过匿名对象的方式创建该对象
        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             * invoke方法 是执行我们target_vehicle的方法时 会调用到
             * @param o 表示代理对象
             * @param method 就是通过代理对象调用方法时 的对应方法
             * @param args 调用代理对象方法时传入的参数
             * @return 调用代理对象方法 返回执行的结果
             * @throws Throwable
             */
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                System.out.println("交通工具开始运行...");
                //反射基础  target_vehicle 是Ship对象
                //method=Vehicle.run()  target_vehicle = Ship对象  args = null
                Object result = method.invoke(target_vehicle, args);
                System.out.println("交通工具停止运行。。");
                return result;
            }
        };

        Vehicle proxy = (Vehicle)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return proxy;
    }
}
