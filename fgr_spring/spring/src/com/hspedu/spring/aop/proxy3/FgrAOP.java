package com.hspedu.spring.aop.proxy3;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 自己编写 的极简AOP
 */
public class FgrAOP {

    public static void before(Object proxy, Method method, Object[] args){
        System.out.println("方法执行开始-日志-方法名-" +
                method.getName() + "-参数 [" + Arrays.asList(args) + "]");

    }

    public static void after( Method method, Object result){
        System.out.println("方法执行正常结束-日志-方法名-"
                + method.getName() + "-结果 result=" + result);


    }

}
