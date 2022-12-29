package com.hspedu.spring2.ioc;

import com.hspedu.spring2.annotation.Autowired;
import com.hspedu.spring2.annotation.Component;
import com.hspedu.spring2.annotation.ComponentScan;
import com.hspedu.spring2.annotation.Scope;
import com.hspedu.spring2.processor.BeanPostProcessor;
import com.hspedu.spring2.processor.InitializingBean;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class FgrSpringApplicationContext {
    private Class configClass;

    //beanDefinitionMap
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //单例池
    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    //后置处理器的集合
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    //构造器
    public FgrSpringApplicationContext(Class configClass) {
        //所有注解对象扫描进beanDefinitionMap
        beanDefinitionByScan(configClass);

        //遍历beanDefinitionMap 将singleton放入单例池
        Enumeration<String> keys = beanDefinitionMap.keys();
        while (keys.hasMoreElements()) {
            String beanName = keys.nextElement();

            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equals(beanDefinition.getScope())) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    public void beanDefinitionByScan(Class configClass) {
        this.configClass = configClass;
//        System.out.println("this.configClass" + this.configClass);
        //获取要扫描的包
        //1.先得到FgrConfig配置的包 com.hspedu.spring.component
        ComponentScan componentScan = (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);
        //得到注解的value
        String path = componentScan.value();
        System.out.println("要扫描的包=" + path);

        //得到要扫描的包下的资源(类.class)
        //1.先得到类的加载器
        ClassLoader classLoader = FgrSpringApplicationContext.class.getClassLoader();
        //2.通过类的加载器获取到要扫描包的资源url
        path = path.replace(".", "/");//一定将.替换成/  路径符号路径
        URL resource = classLoader.getResource(path);//Resource代表真实路径 不是src
        //3.将要加载的资源(.class) 路径下的文件进行遍历
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
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
//                    System.out.println(classFullName);

                    //判断该类是否需要注入到容器中  也就是有注解并符合指定才进行注入
                    try {
                        //这时 我们就得到了该类的class对象
                        //Class.ForName(classFullName) 也可以
                        //两种通过反射的类加载区别在于上面方式会调用该类静态资源 而下面的则不会
                        Class<?> aClass = classLoader.loadClass(classFullName);
                        if (aClass.isAnnotationPresent(Component.class)) {

                            //当前clazz有没有实现我们定义的beanPostProcessor接口
                            //合理不能使用instanceof 现在的clazz不是实例对象 是类对象 使用isAssignableFrom
                            if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                                BeanPostProcessor instance = (BeanPostProcessor) aClass.newInstance();
                                beanPostProcessorList.add(instance);
                                //继续保持循环，但忽略当前循环以下的语句
                                continue;
                            }
                            //演示Component注解指定value  分配为id
                            //演示了一个的机制，
                            if (aClass.isAnnotationPresent(Component.class)) {
                                //获取到该注解
                                Component declaredAnnotation = aClass.getDeclaredAnnotation(Component.class);
                                String id = declaredAnnotation.value();
                                if (!"".endsWith(id)) {
                                    className = id;
                                }
                            }
                            //类名的首字母小写 作为id
                            className = StringUtils.uncapitalize(className);
                            //得到类注解是否存在scope=prototype  不存在scope为singleton 将scope放入beanDefinition
                            BeanDefinition beanDefinition = null;
                            if (aClass.isAnnotationPresent(Scope.class)) {
                                Scope declaredAnnotation = aClass.getDeclaredAnnotation(Scope.class);
                                String scope = declaredAnnotation.value();
                                System.out.println("scope" + scope);
                                beanDefinition = new BeanDefinition(aClass, scope);
                            } else {//没有scope就视为单例
                                beanDefinition = new BeanDefinition(aClass, "singleton");
                            }

                            beanDefinitionMap.put(className, beanDefinition);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    //创建bean对象  放入单例池
    public Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
//                singletonObjects.put(beanName,instance);
            //拿到对象所有字段 进行遍历 注解是否为Autowired
            Field[] declaredFields = beanDefinition.getClazz().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    String name = declaredField.getName();
                    //getBean获取要组住哪个的对象
                    Object bean = getBean(name);
                    //字段要暴力破解
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }

            //后置处理器的前置方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                Object bean =
                        beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
                if (bean != null){
                    instance = bean;
                }
            }
            //执行初始化方法
            if (instance instanceof InitializingBean) {
                try {
                    ((InitializingBean) instance).afterPropertiesSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //后置处理器的后置方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                Object bean =
                        beanPostProcessor.postProcessAfterInitialization(instance, beanName);
                if (bean != null){
                    instance = bean;
                }
            }
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //反射对象失败
        return null;
    }

    //编写方法返回容器中对象
    public Object getBean(String name) {
        if (beanDefinitionMap.containsKey(name)) {
            //如果bean的scope是singleton，则去单例池singletonObjects拿实例
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            if ("singleton".equals(beanDefinition.getScope())) {
                Object bean = singletonObjects.get(name);
                return bean;
            } else {
                Object bean = createBean(name, beanDefinitionMap.get(name));
                return bean;
            }
        } else {
            throw new NullPointerException("beanDefinitionMap中不存在这个bean");
        }
    }
}
