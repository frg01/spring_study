package com.hspedu.spring2.component;

import com.hspedu.spring2.annotation.Autowired;
import com.hspedu.spring2.annotation.Scope;
import com.hspedu.spring2.annotation.Component;
import com.hspedu.spring2.processor.InitializingBean;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Scope(value = "prototype")
@Component(value = "monsterService")
public class MonsterService implements InitializingBean {
    @Autowired
    private MonsterDao monsterDao;
    public void m1(){
        monsterDao.hi();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("这是MonsterService的一个初始化方法");
    }
}
