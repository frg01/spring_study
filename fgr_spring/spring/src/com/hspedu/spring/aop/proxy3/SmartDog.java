package com.hspedu.spring.aop.proxy3;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class SmartDog implements SmartAnimal {
    @Override
    public Float getSum(float i, float j) {
        float result = i + j;
//        System.out.println("方法执行开始-日志-方法名-getSum-参数 [" +i + "," + j + "]");

//        System.out.println("方法执行正常结束-日志-方法名-getSum-结果 result=" + result);
//        System.out.println("方法最终结束-日志-方法名-getSum");
        return result;
    }

    @Override
    public Float getSub(float i, float j) {
        float result = i - j;
//        System.out.println("方法执行开始-日志-方法名-getSub-参数 [" +i + "," + j + "]");
//        System.out.println("方法内部打印 result = " + result);
//        System.out.println("方法执行正常结束-日志-方法名-getSub-结果 result=" + result);
//        System.out.println("方法最终结束-日志-方法名-getSub");
        return result;
    }
}
