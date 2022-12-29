package com.hspedu.spring2.component;

import com.hspedu.spring2.annotation.Component;
import com.hspedu.spring2.processor.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Component
public class FgrBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("before后置处理器postProcessBeforeInitialization" + bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("after后置处理器postProcessAfterInitialization" + bean.getClass());
        if ("smartDog".equals(beanName)){
            Object proxyInstance = Proxy.newProxyInstance(FgrBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("method= " + method.getName());
                    Object invoke = null;
                    if ("getSum".equals(method.getName())){
                        SmartAnimalAspect.showBeginLog();
                        invoke = method.invoke(bean, args);
                        SmartAnimalAspect.showSuccessLog();
                    }else {
                        invoke = method.invoke(bean, args);
                    }
                    return invoke;
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}
