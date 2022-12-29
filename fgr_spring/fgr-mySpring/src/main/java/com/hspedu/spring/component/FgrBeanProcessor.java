package com.hspedu.spring.component;

import com.hspedu.spring.annotation.Component;
import com.hspedu.spring.processor.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 自己的后置处理器 重写接口方法
 * 在spring容器中仍然把FgrBeanProcessor当作一个Bean对象  注入到容器中
 * @Component标识
 * 使其成为后置处理器  在容器中加入业务代码
 * 还要考虑多个后置处理器对象注入容器的问题 先后顺序
 */
@Component
public class FgrBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        //后置处理器对容器创建的bean生效
        //相当于可以对多个对象编程
        //日志，权限，身份，事务。。。。
        if (bean instanceof Car){
            System.out.println("这时一个Car对象，我可以处理");
        }
        System.out.println("后置处理器FgrBeanProcessor的before方法调用。。"
                +bean.getClass() + "bean的名字" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("后置处理器FgrBeanProcessor的After方法调用。。"
                +bean.getClass() + "bean的名字" + beanName);

        //实现AOP，返回代理对象  对Bean进行包装 先死后活 后面通过注解更灵活
        if ("smartDog".equals(beanName)){//判端需不需要返回代理对象
            //使用JDK动态代理，返回bean代理对象
            Object proxyInstance = Proxy.newProxyInstance(FgrBeanProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("method" + method.getName());

                    Object invoke = null;
                    //假定要进行前置通知处理的方法是getSum()
                    //可以通过注解处理的更加灵活
                    if ("getSum".equals(method.getName())) {
                        SmartAnimalAspect.showBeginLog();
                        invoke = method.invoke(bean, args);//执行目标方法
                        //返回通知的处理
                        SmartAnimalAspect.showSuccessLog();
                    } else {
                        invoke = method.invoke(bean, args);//执行目标方法
                    }
                    return invoke;
                }
            });
            //如果bean是需要返回代理对象的 直接return
            return proxyInstance;
        }
        //不需要AOP就返回bean
        return bean;
    }
}
