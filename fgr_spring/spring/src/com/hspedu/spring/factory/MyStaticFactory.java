package com.hspedu.spring.factory;

import com.hspedu.spring.bean.Monster;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 静态工厂 返回Monster对象
 */
public class MyStaticFactory {
    private static Map<String,Monster> monsterMap;

    //使用静态代码块进行初始化
    static {
        monsterMap = new HashMap<>();
        monsterMap.put("monster01", new Monster(104, "黑熊精", "盾击"));
        monsterMap.put("monster02", new Monster(105, "狐狸精", "魅惑"));
    }

    //返回Monster对象
    public static Monster getMonster(String key){
        return monsterMap.get(key);
    }
}
