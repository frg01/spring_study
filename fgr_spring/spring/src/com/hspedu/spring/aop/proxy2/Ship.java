package com.hspedu.spring.aop.proxy2;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class Ship implements Vehicle{
    @Override
    public void run() {
//        System.out.println("交通工具开始运行...");
        System.out.println("船在水上running。。");
//        System.out.println("交通工具停止运行。。");

    }
}
