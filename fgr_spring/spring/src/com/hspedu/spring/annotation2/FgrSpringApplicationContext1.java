package com.hspedu.spring.annotation2;

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
 * ioc 容器
 */
public class FgrSpringApplicationContext1 {
    private Class configClass;
    //ioc容器
    private final ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();

    public FgrSpringApplicationContext1(Class configClass) {
        this.configClass = configClass;

        ComponentScan1 componentScan1 = (ComponentScan1)this.configClass.getDeclaredAnnotation(ComponentScan1.class);
        //得到全名称com.hspedu.spring.component
        String path = componentScan1.value();

        //全名称转换成路径
        path = path.replace(".", "/");
        System.out.println("path" + path);
        //获得类的加载器
        ClassLoader classLoader = FgrSpringApplicationContext1.class.getClassLoader();
        URL resource = classLoader.getResource(path);
//        System.out.println(resource);

        //对路径下的文件进行扫描
        File file = new File(resource.getFile());
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                //获取文件的全路径方便去反射
                String absolutePath = f.getAbsolutePath();
//                System.out.println(absolutePath);
                //只处理.class文件
                if (absolutePath.endsWith(".class")){
                    //取出类名
                    String className =
                            absolutePath.substring(absolutePath.lastIndexOf("\\") + 1, absolutePath.indexOf(".class"));
                    path = path.replace("/", ".");

                    String classFullName = path + "." + className;
                    //判断类是否符合加载到容器中的注解条件

                    try {
                        Class<?> aClass = classLoader.loadClass(classFullName);
                        if (aClass.isAnnotationPresent(Component.class) ||
                                aClass.isAnnotationPresent(Controller.class) ||
                                aClass.isAnnotationPresent(Repository.class) ||
                                aClass.isAnnotationPresent(Service.class)){

                            if (aClass.isAnnotationPresent(Component.class)){
                                Component declaredAnnotation = aClass.getDeclaredAnnotation(Component.class);
                                String id = declaredAnnotation.value();
                                className = id;
                            }
                            //进行反射
                            Class<?> clazz = Class.forName(classFullName);
                            Object instance = clazz.newInstance();

                            //放入容器中
                            ioc.put(StringUtils.uncapitalize(className),instance);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }
        }
    }

    //getBeans方法
     public Object getBean(String name){
        return ioc.get(name);
     }
}
