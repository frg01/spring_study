package com.hspedu.spring.ioc;

import com.hspedu.spring.annotation.Autowired;
import com.hspedu.spring.annotation.Component;
import com.hspedu.spring.annotation.ComponentScan;
import com.hspedu.spring.annotation.Scope;
import com.hspedu.spring.processor.BeanPostProcessor;
import com.hspedu.spring.processor.InitializingBean;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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


    //定义BeanDefinitionMap  存BeanDefinition对象
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //定义Singleton 存放单例对象
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    //定义一个属性beanPostProcessorList ， 存放后置处理器[]
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    //构造器
    public FgrSpringApplicationContext(Class configClass) {
        //完成了扫描指定的包
        beanDefinitionByScan(configClass);

        //通过beanDefinitionMap  初始化singletonObjects单例池
        //封装成方法  遍历枚举
        Enumeration<String> keys = beanDefinitionMap.keys();//所有的beanName
        while (keys.hasMoreElements()) {
            String beanName = keys.nextElement();//拿到beanName
            //通过BeanName 得到对应的beanDefinition
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equals(beanDefinition.getScope())) {
                //将该bean实例化 放入singletonObject
                Object bean = createBean(beanName,beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
//        System.out.println("singletonObject 单例池=" + singletonObjects);
//        System.out.println(beanDefinitionMap);
    }

    //完成对指定包的扫描  bean信息封装到BeanDefinition 在放入到Map中
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
                        Class<?> clazz = classLoader.loadClass(classFullName);
                        if (clazz.isAnnotationPresent(Component.class)) {
                            //如果该类使用Component 说明是spring Bean
                            System.out.println("这是一个springBean =" + clazz);

                            //将后置处理器放置ArrayList
                            //如果发现是后置处理器 放入到beanPostProcessorList
                            //在原生的spring容器中，还是走getBean 和createBean 但需要在singletonObject加入相应的业务
                            //当前clazz有没有实现我们定义的beanPostProcessor接口
                            //合理不能使用instanceof 现在的clazz不是实例对象 是类对象 使用isAssignableFrom
                            //实现BeanPostProcessor的对象不用放入singletonObjects
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)){
                                BeanPostProcessor instance =
                                        (BeanPostProcessor)clazz.newInstance();
                                //放入到beanPostProcessorList
                                beanPostProcessorList.add(instance);
                                continue;
                            }
                            //先得到beanName 先得到component注解
                            Component declaredAnnotation = clazz.getDeclaredAnnotation(Component.class);
                            String beanName = declaredAnnotation.value();
                            if ("".equals(beanName)) {//如果没有配置value=beanName
                                //将类名首字母小写作为beanName
                                beanName = StringUtils.uncapitalize(className);
                            }
                            //将bean的信息封装到BeanDefinition
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);
                            //获取scope
                            if (clazz.isAnnotationPresent(Scope.class)) {
                                //配置了scope 获取指定值
                                Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotation.value());
                            } else {
                                //如果没有配置，就默认值singleton
                                beanDefinition.setScope("singleton");
                            }

                            //beanDefinition 对象放入到Map
                            beanDefinitionMap.put(beanName, beanDefinition);

                        } else {
                            //如果该类没有使用Component 说明不是spring Bean
                            System.out.println("这不是一个springBean =" + clazz);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //完成createBean(BeanDefinition beanDefinition)方法
    public Object createBean(String beanName,BeanDefinition beanDefinition) {
        //得到Bean的clazz对象
        Class clazz = beanDefinition.getClazz();

        //反射得到实例
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            //遍历所有字段 上的注解
            for(Field declaredField : clazz.getDeclaredFields()){
                //判断是否有Autowired修饰
                if (declaredField.isAnnotationPresent(Autowired.class)){
                    //处理@Autowired 的required
                    Autowired annotation = declaredField.getAnnotation(Autowired.class);
                    if (annotation.required()){
                        //得到字段的名字
                        String name = declaredField.getName();
                        //通过getBean方法获取要组装的对象
                        Object bean = getBean(name);
                        //进行组装  instance:创建的实例  bean依赖注入实例中的属性对象
                        declaredField.setAccessible(true);//属性私有的，需要进行暴力破解
                        declaredField.set(instance,bean);
                    }
                }

            }
            System.out.println("====创建好实例====" + instance);

            //在bean的初始化方法前  调用后置处理器的before方法
            //原生spring容器 比这个复杂
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                //对bean实例进行处理
                //返回处理后的bean实例 相当于前置处理
                Object current =
                        beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
                if (current != null){
                    instance = current;
                }
            }

            //这里判断是否执行bean的初始化方法
            //判断当前备案实例是否实现了InitializingBean
            //instanceof 判断某个对象的运行类型是不是某个类型 或者某个类型的子类型
            //这里使用到接口编程形式
            if (instance instanceof InitializingBean){
                //将instanceof转成InitializingBean
                try {
                    ((InitializingBean)instance).afterPropertiesSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //在bean的初始化方法后  调用后置处理器的after方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                //对bean实例进行处理
                //返回处理后的bean实例 相当于前置处理
                Object current =
                        beanPostProcessor.postProcessAfterInitialization(instance, beanName);
                if (current != null){
                    instance = current;
                }
            }
            System.out.println("-----------------------------------");
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

        //如果反射对象失败
        return null;

    }

    //编写方法返回容器中对象 传入beanName
    public Object getBean(String name) {

        //加一个判断 beanDefinitionMap中是否存在beanName
        if (beanDefinitionMap.containsKey(name)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            //得到beanDefinition 的scope
            if ("singleton".equals(beanDefinition.getScope())) {
                //说明是单例  直接从单例池获取
                return singletonObjects.get(name);
            } else {//如果不是单例  就调用createBean
                return createBean(name,beanDefinition);
            }
        }else{
            //抛出空指针异常  或自定义异常
            throw new NullPointerException("没有该bean");
        }
    }
}
