package com.hspedu.spring2.component;

import com.hspedu.spring2.annotation.Component;
import com.hspedu.spring2.annotation.Scope;

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
