package com.hspedu.spring.hspapplicationContext;

import com.hspedu.spring.bean.Monster;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guorui fu
 * @versiion: 1.0
 *实现Spring的一个简单容器
 * 实现如何将benas.xml进行解析，并生成对象 放入容器
 * 提供一个方法 getBean() 返回对应对象
 */
public class HspApplicationContext {
    private ConcurrentHashMap<String,Object> singletonObject =
            new ConcurrentHashMap<>();

    //接收一个容器的配置文件 在src下
    public HspApplicationContext(String iocBeanXmlFile) throws Exception {
        //得到类加载路径
        String path = this.getClass().getResource("/").getPath();

        //创建SAXReader
        SAXReader saxReader = new SAXReader();

        //得到document对象
        Document read =
                saxReader.read(new File(path + iocBeanXmlFile));

        //得到根元素
        Element rootElement = read.getRootElement();
        Element bean = (Element)rootElement.elements("bean").get(0);

        //获取到第一个bean的monster01相关属性
        String id = bean.attributeValue("id");
        String classFullPath = bean.attributeValue("class");

        List<Element> property = bean.elements("property");
        Integer monsterId = Integer.parseInt(property.get(0).attributeValue("value"));
        String name = property.get(1).attributeValue("value");
        String skill = property.get(2).attributeValue("value");

        //使用反射创建对象 => 反射机制
        Class<?> aClass = Class.forName(classFullPath);

        //返回了一个Monster 默认值
        Monster o = (Monster)aClass.newInstance();

        //使用反射给o对象赋值
//        Method[] declaredMethods = aClass.getDeclaredMethods();
//        for (Method declaredMethod : declaredMethods) {
//            declaredMethod.invoke(o);
//        }

        o.setMonsterId(monsterId);
        o.setName(name);
        o.setSkill(skill);

        //将创建号的对象放入singletonObject
        singletonObject.put(id,o);
    }

    public Object getBean(String id) {
        return singletonObject.get(id);
    }
}
