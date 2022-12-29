package com.hspedu.spring.aop.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class VehicleProxyProvider2 {
    private Vehicle vehicle;

    public VehicleProxyProvider2(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getProxy(){

        //
        ClassLoader classLoader = VehicleProxyProvider2.class.getClassLoader();
        //2.Class<?>[] interfaces,
        Class<?>[] interfaces = vehicle.getClass().getInterfaces();
        //3.InvocationHandler h
        InvocationHandler invocationHandler = new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("交通工具开始运行...");
                Object result = method.invoke(vehicle, args);
                System.out.println("交通工具停止运行。。");

                return result;
            }
        };

        Vehicle proxy = (Vehicle)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return proxy;
    }
}
