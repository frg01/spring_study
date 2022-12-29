package com.hspedu.spring.ioc;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * BeanDefinition 封装记录bean的信息【1.scope 2.bean对应的class对象，反射可以生成对应的对象】
 */
public class BeanDefinition {

    private String scope;
    private Class clazz;
    //根据需求扩展


    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "scope='" + scope + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
