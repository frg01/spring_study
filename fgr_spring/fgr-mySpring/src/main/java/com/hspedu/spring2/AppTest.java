package com.hspedu.spring2;

import com.hspedu.spring2.component.MonsterDao;
import com.hspedu.spring2.component.MonsterService;
import com.hspedu.spring2.component.SmartAnimal;
import com.hspedu.spring2.component.SmartDog;
import com.hspedu.spring2.ioc.FgrSpringApplicationContext;
import com.hspedu.spring2.ioc.FgrSpringConfig;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class AppTest {
    public static void main(String[] args) {

        FgrSpringApplicationContext fgrSpringApplicationContext
                = new FgrSpringApplicationContext(FgrSpringConfig.class);

        MonsterService monsterService
                = (MonsterService)fgrSpringApplicationContext.getBean("monsterService");
        monsterService.m1();
        System.out.println("---------------------");
        SmartAnimal smartDao =
                (SmartAnimal)fgrSpringApplicationContext.getBean("smartDog");
        smartDao.getSum(10,2);
        smartDao.getSub(10,2);
        System.out.println("ok");
    }
}
