package com.hspedu.spring;

import com.hspedu.spring.component.MonsterDao;
import com.hspedu.spring.component.MonsterService;
import com.hspedu.spring.component.SmartAnimal;
import com.hspedu.spring.ioc.FgrSpringApplicationContext;
import com.hspedu.spring.ioc.FgrSpringConfig;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class AppMain {
    public static void main(String[] args) {
        FgrSpringApplicationContext fgrSpringApplicationContext =
                new FgrSpringApplicationContext(FgrSpringConfig.class);

        //测试依赖注入
        MonsterService monsterService =
                (MonsterService)fgrSpringApplicationContext.getBean("monsterService");

        monsterService.m1();

//        MonsterService monsterService = (MonsterService)fgrSpringApplicationContext.getBean("monsterService");
//        MonsterService monsterService2 = (MonsterService)fgrSpringApplicationContext.getBean("monsterService");
//        System.out.println("monsterService" + monsterService);
//        System.out.println("monsterService2" + monsterService2);
//
//        MonsterDao monsterDao = (MonsterDao)fgrSpringApplicationContext.getBean("monsterDao");
//        MonsterDao monsterDao2 = (MonsterDao)fgrSpringApplicationContext.getBean("monsterDao");
//        System.out.println("MonsterDao=" + monsterDao);
//        System.out.println("MonsterDao2=" + monsterDao);
        SmartAnimal smartDog = (SmartAnimal)fgrSpringApplicationContext.getBean("smartDog");
        System.out.println("smartDog=" + smartDog.getClass());
        smartDog.getSum(10,2);
        smartDog.getSub(10,2);
        System.out.println("ok");
    }
}
