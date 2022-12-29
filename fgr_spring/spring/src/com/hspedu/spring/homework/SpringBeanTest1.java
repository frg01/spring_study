package com.hspedu.spring.homework;

import com.hspedu.spring.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class SpringBeanTest1 {

    @Test
    public void getMonster() throws ClassNotFoundException {
        //创建容器 ApplicationContext
        //该容器和容器配置文件关联
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

        //通过getBean获取对应的对象  默认返回Object  但运行类型是Monster
        Monster monster = ioc.getBean("com.hspedu.spring.bean.Monster#0", Monster.class);
        System.out.println("monster" + monster);
        System.out.println("monsterId" + monster.getMonsterId());

    }

    //验证类加载路径
    @Test
    public void classPath(){
        File file = new File(this.getClass().getResource("/").getPath());
        //类的加载路径
        System.out.println("file=" + file);
    }
}
