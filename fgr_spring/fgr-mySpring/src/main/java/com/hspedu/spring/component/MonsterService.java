package com.hspedu.spring.component;

import com.hspedu.spring.annotation.Autowired;
import com.hspedu.spring.annotation.Component;
import com.hspedu.spring.annotation.Scope;
import com.hspedu.spring.processor.InitializingBean;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * MonsterService 是一个Service
 * 指定value 注入容器以指定为主  没有指定value 则使用类名首字母小写名字
 */
@Scope(value = "prototype")
@Component(value = "monsterService")//MonsterService 注入我们自己的spring容器中
public class MonsterService implements InitializingBean {
    //使用自己的注解修饰属性 表示该属性是通过容器完成依赖注入  实现按照名字进行组装
    @Autowired
    private MonsterDao monsterDao;

    public void m1(){
        monsterDao.hi();
    }

    //Bean的初始化方法 bean的setter方法执行之后被调用  容器调用  即初始化方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("monsterService 初始化方法被调用，可添加自己的业务");
    }
}
