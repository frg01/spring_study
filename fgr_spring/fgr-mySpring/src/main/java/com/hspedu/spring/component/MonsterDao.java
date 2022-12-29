package com.hspedu.spring.component;

import com.hspedu.spring.annotation.Component;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */

@Component(value = "monsterDao")
public class MonsterDao {

    public void hi(){
        System.out.println("MonsterDao-hi()");
    }
}
