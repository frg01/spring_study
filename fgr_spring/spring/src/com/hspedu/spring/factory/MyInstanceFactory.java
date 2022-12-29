package com.hspedu.spring.factory;

import com.hspedu.spring.bean.Monster;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class MyInstanceFactory {
    private Map<String, Monster> monster_map;

    //通过普通代码块进行初始化
    {
        monster_map = new HashMap<>();
        monster_map.put("monster03", new Monster(106, "黑熊精·", "盾击·"));
        monster_map.put("monster04", new Monster(107, "狐狸精·", "魅惑·"));
    }

    //写一个方法返回monster对象
    public Monster getMonster(String key) {
        return monster_map.get(key);
    }
}
