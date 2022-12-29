package com.hspedu.spring.annotation;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * FgrSpringApplicationContext类 的作用类似Spring原生ioc容器
 */
public class FgrSpringApplicationContext {
    private Class configClass;
    //存放通过反射创建的对象
    private final ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();

    //构造器
    public FgrSpringApplicationContext(Class configClass) {
        this.configClass = configClass;
//        System.out.println("this.configClass" + this.configClass);
        //获取要扫描的包
        //1.先得到FgrConfig配置的包 com.hspedu.spring.component
        ComponentScan componentScan = (ComponentScan)this.configClass.getDeclaredAnnotation(ComponentScan.class);
        //得到注解的value
        String path = componentScan.value();
        System.out.println("要扫描的包=" + path);

        //得到要扫描的包下的资源(类.class)
        //1.先得到类的加载器
        ClassLoader classLoader = FgrSpringApplicationContext.class.getClassLoader();
        //2.通过类的加载器获取到要扫描包的资源url
        path = path.replace(".","/");//一定将.替换成/  路径符号路径
        URL resource = classLoader.getResource(path);//Resource代表真实路径 不是src
        //3.将要加载的资源(.class) 路径下的文件进行遍历
        File file = new File(resource.getFile());
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                //文件的绝对路径 但反射需要类的全路径
                String fileAbsolutePath = f.getAbsolutePath();

                //这里我们只处理.class文件
                if (fileAbsolutePath.endsWith(".class")) {
                    //先获取到类名
                    String className =
                            fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1, fileAbsolutePath.indexOf(".class"));
//                System.out.println("classsName" + className);
                    //获取类的全类名
                    String classFullName = path.replace("/", ".") + "." + className;
                System.out.println(classFullName);

                    //判断该类是否需要注入到容器中  也就是有注解并符合指定才进行注入
                    try {
                        //这时 我们就得到了该类的class对象
                        //Class.ForName(classFullName) 也可以
                        //两种通过反射的类加载区别在于上面方式会调用该类静态资源 而下面的则不会
                        Class<?> aClass = classLoader.loadClass(classFullName);
                        if (aClass.isAnnotationPresent(Component.class) ||
                                aClass.isAnnotationPresent(Controller.class) ||
                                aClass.isAnnotationPresent(Service.class)||
                                aClass.isAnnotationPresent(Repository.class)){

                            //演示Component注解指定value  分配为id
                            //演示了一个的机制，
                            if (aClass.isAnnotationPresent(Component.class)){
                                //获取到该注解
                                Component declaredAnnotation = aClass.getDeclaredAnnotation(Component.class);
                                String id = declaredAnnotation.value();
                                if (!"".endsWith(id)){
                                    className = id;
                                }
                            }
                            //这时就可以反射对象 并放入到容器中
                            Class<?> clazz = Class.forName(classFullName);
                            Object instance = clazz.newInstance();
                            //类名的首字母小写 作为id

                            ioc.put(StringUtils.uncapitalize(className),instance);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //编写方法返回容器中对象
    public Object getBean(String name){
        return ioc.get(name);
    }
}
