package com.hspedu.spring.factory;

import com.hspedu.spring.bean.Monster;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class MyFactoryBean implements FactoryBean<Monster> {

    //这个key就是配置时候 指定要获取的对象对应key
    private String key;
    private Map<String,Monster> monster_map;

    {   //代码块  完成初始化
        monster_map = new HashMap<>();
        monster_map.put("monster03",new Monster(300,"牛魔王","芭蕉扇·"));
        monster_map.put("monster04",new Monster(400,"狐狸精","美人计·"));
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Monster getObject() throws Exception {
        return monster_map.get(key);
    }

    @Override
    public Class<?> getObjectType() {
        return Monster.class;
    }

    @Override
    public boolean isSingleton() {//指定是否返回是单例
        return true;
    }
}
