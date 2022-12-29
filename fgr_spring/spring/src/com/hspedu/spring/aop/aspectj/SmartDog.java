package com.hspedu.spring.aop.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Component  //当spring容器启动 将此类注入容器
public class SmartDog implements SmartAnimal {
    @Override
    public Float getSum(float i, float j) {
        float result = i + j;
        System.out.println("方法内部result=" + result);
        return result;
    }

    @Override
    public Float getSub(float i, float j) {
        float result = i - j;
        System.out.println("方法内部result=" + result);
        return result;
    }
}
