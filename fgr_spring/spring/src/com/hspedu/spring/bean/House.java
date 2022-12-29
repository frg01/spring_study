package com.hspedu.spring.bean;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class House {
    private String name;

    public House(){
        System.out.println("House()  构造器");
    }

    public void setName(String name){
        System.out.println("House setName()..." + name);
        this.name = name;
    }

    //这个方法自主编写 根据业务逻辑 名字不固定
    public void init() {
        System.out.println("House init()...");
    }

    //这个方法也自主编写 根据业务逻辑 名字也不固定
    public void destroy() {
        System.out.println("House destroy()...");
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                '}';
    }
}
