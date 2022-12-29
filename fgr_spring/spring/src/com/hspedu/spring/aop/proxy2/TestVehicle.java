package com.hspedu.spring.aop.proxy2;

import org.junit.jupiter.api.Test;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class TestVehicle {


    @Test
    public void proxyRun(){
        Vehicle vehicle = new Car();
        //VehicleProxyProvider创建  传入要代理的对象
//        VehicleProxyProvider vehicleProxyProvider =
//                new VehicleProxyProvider(vehicle);

        //获取代理对象,可以帮助我们代理执行方法
        //proxy编译类型是Vehicle，运行类型是代理运行class com.sun.proxy.$Proxy9
        //被代理的对象  和 方法 可以体现多态
//        Vehicle proxy = vehicleProxyProvider.getProxy();
//        proxy.run();

        VehicleProxyProvider2 vehicleProxyProvider2 =
                new VehicleProxyProvider2(vehicle);
        Vehicle proxy = vehicleProxyProvider2.getProxy();
        proxy.run();

    }


    @Test
    public void run(){
        Vehicle vehicle = new Ship();
        vehicle.run();
    }
}
