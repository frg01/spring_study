package com.hspedu.spring.annotatiion3;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class ComponentApplicationContext2 {
    private Class configClass;
    private final ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();

    public ComponentApplicationContext2(Class configClass) {
        this.configClass = configClass;

        ComponentScan2 componentScan2 = (ComponentScan2)this.configClass.getDeclaredAnnotation(ComponentScan2.class);

        String path = componentScan2.value();

        path = path.replace(".", "/");

        //使用类的加载器获取out下的全路径
        ClassLoader classLoader = ComponentApplicationContext2.class.getClassLoader();
        URL fullPath = classLoader.getResource(path);
//        System.out.println(fullPath);

        //路径可以做文件，文件也是一种路径
        File file = new File(fullPath.getFile());
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                //从绝对路径中取出对象类名
                String absolutePath = f.getAbsolutePath();
                if (absolutePath.endsWith(".class")){
                    String className =
                            absolutePath.substring(absolutePath.lastIndexOf("\\") + 1, absolutePath.indexOf(".class"));
                    //path在改回全类名方式
                    path = path.replace("/", ".");
                    //完整的全类名
                    String classFullName = path + "." + className;
                    System.out.println(classFullName);

                    try {
                        //使用加载器先判断class文件是否有符合的注解
                        Class<?> aClass = classLoader.loadClass(classFullName);
                        if (aClass.isAnnotationPresent(Component.class) ||
                            aClass.isAnnotationPresent(Controller.class) ||
                            aClass.isAnnotationPresent(Repository.class) ||
                                aClass.isAnnotationPresent(Service.class)){

                            //注解中给对象指定了id，可以通过id获取对象
                            if (aClass.isAnnotationPresent(Component.class)){
                                Component declaredAnnotation = aClass.getDeclaredAnnotation(Component.class);
                                String id = declaredAnnotation.value();
                                className = id;
                            }

                            Class<?> aClass1 = Class.forName(classFullName);
                            Object instance = aClass1.newInstance();
                            ioc.put(StringUtils.uncapitalize(className),instance);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    public Object getBean(String name){
        return ioc.get(name);
    }
}
