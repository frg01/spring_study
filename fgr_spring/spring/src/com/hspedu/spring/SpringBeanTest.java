package com.hspedu.spring;

import com.hspedu.spring.bean.*;
import com.hspedu.spring.component.MyComponent;
import com.hspedu.spring.component.UserAction;
import com.hspedu.spring.component.UserDao;
import com.hspedu.spring.component.UserService;
import com.hspedu.spring.depinjection.BookService;
import com.hspedu.spring.depinjection.PhoneService;
import com.hspedu.spring.service.MemberServiceImpl;
import com.hspedu.spring.web.OrderAction;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class SpringBeanTest {

    //通过泛型依赖配置bean
    @Test
    public void setProByDependencyInjection(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("beans07.xml");

        PhoneService poneService = ioc.getBean("phoneService", PhoneService.class);
        poneService.save();
        BookService bookService = ioc.getBean("bookService", BookService.class);
        bookService.save();
        System.out.println("ok");

    }

    //通过注解来配置Bean
    @Test
    public void setProByAutowired() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans06.xml");

        UserAction userAction = ioc.getBean("userAction", UserAction.class);
        System.out.println("userAction" + userAction);
        userAction.sayOk();

    }


    //通过注解来配置Bean
    @Test
    public void setBeanByAnnotation() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans05.xml");

        UserDao userDao = ioc.getBean(UserDao.class);
        UserService userService = ioc.getBean(UserService.class);
        UserAction userAction = ioc.getBean(UserAction.class);
        MyComponent myComponent = ioc.getBean(MyComponent.class);
        System.out.println("userDao" + userDao + "\nuserService" + userService +
                "\nuserAction" + userAction + "\nmyComponent" + myComponent);


    }

    //通过spring el 对属性赋值
    @Test
    public void setBeanBySpringEl() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans04.xml");

        SpElBean spElBean = ioc.getBean("spElBean", SpElBean.class);

        System.out.println(spElBean);
    }

    //测试后置处理器
    @Test
    public void setBeanByAutowire() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans03.xml");

        OrderAction orderAction = ioc.getBean("orderAction", OrderAction.class);

        System.out.println(orderAction.getOrderService());
        System.out.println(orderAction.getOrderService().getOrderDao());
    }

    //测试后置处理器
    @Test
    public void setBeanByFile() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans03.xml");

        Monster monster1000 = ioc.getBean("monster1000", Monster.class);
        System.out.println("monster1000" + monster1000);
    }

    //测试后置处理器
    @Test
    public void testBeanPostProcessor() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans02.xml");

        House house = ioc.getBean("house", House.class);
        House house2 = ioc.getBean("house2", House.class);

        System.out.println("使用house" + house);
        System.out.println("house2=" + house2);

        ((ConfigurableApplicationContext) ioc).close();

    }


    //bean的生命周期
    @Test
    public void TestBeanLifeTime(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        House house = ioc.getBean("house", House.class);
        System.out.println("使用house" + house);

        //关闭容器
        //java基础  接口接收指定编译类型 更加灵活
        ((ConfigurableApplicationContext)ioc).close();

    }

    //测试单例与多例
    @Test
    public void testBeanScope(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Cat cat = ioc.getBean("cat", Cat.class);
        Cat cat1 = ioc.getBean("cat", Cat.class);
        Cat cat2 = ioc.getBean("cat", Cat.class);
        System.out.println("cat" + cat);
        System.out.println("cat1" + cat1);
        System.out.println("cat2" + cat2);
    }

    //通过FactoryBean 工厂获取bean
    @Test
    public void getBeanFromFactoryBean(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster04 = ioc.getBean("my_monster04", Monster.class);
        System.out.println("my_monster04=" + my_monster04);
    }

//    配置Bean通过继承
    @Test
    public void getBeanByExtends(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster monster12 = ioc.getBean("monster12", Monster.class);
        System.out.println("monster12" + monster12);
    }

    //通过实例工厂类获取bean
    @Test
    public void setBeanByInstanceFactory(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster02 = ioc.getBean("my_monster02",Monster.class);

        System.out.println("my_monster02=" + my_monster02);
    }

    //通过静态工厂类获取bean
    @Test
    public void setBeanByStaticFactory(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster my_monster01 = ioc.getBean("my_monster01",Monster.class);
        System.out.println("my_monster01" + my_monster01);
    }

//    给属性进行级联赋值
    @Test
    public void setBeanByRelation(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");
        Emp emp = ioc.getBean("emp", Emp.class);

        System.out.println("emp" + emp);
    }


    //使用util:list名称空间给属性赋值
    @Test
    public void setBeanByUtilList(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        BookStore bookStore = ioc.getBean("bookStore", BookStore.class);
        System.out.println("bookStore" + bookStore);
    }

    //来集合数组属性进行赋值
    @Test
    public void setBeanByCollection(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Master master = ioc.getBean("master", Master.class);
        System.out.println("master" + master);
    }


    //来内部的bean设置属性
    @Test
    public void setBeanByProperty(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        MemberServiceImpl memberService = ioc.getBean("memberService2", MemberServiceImpl.class);

        memberService.add();
    }

    //通过ref来设置bean属性
    @Test
    public void setBeanByRef(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        MemberServiceImpl memberService = ioc.getBean("memberService", MemberServiceImpl.class);

        memberService.add();
    }

    //通过p名称空间开设置属性
    @Test
    public void setBeanByP(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster monster06 = ioc.getBean("monster06", Monster.class);

        System.out.println("monster06" + monster06);

    }

    //通过构造器设置属性
    @Test
    public void setBeanByConstructor(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        Monster monster03 = ioc.getBean("monster03", Monster.class);

        System.out.println("monster03" + monster03);

    }


    //通过Bean的类型获取对象 同一个类只能有一个(单例情况)  否则会抛出异常
    @Test
    public void getMonsterByType(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        //传入class对象/类型
        Monster bean = ioc.getBean(Monster.class);
    }

    @Test
    public void getMonster(){

        //创建容器 ApplicationContext
        //该容器和容器配置文件关联
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        //通过getBean获取对应的对象  默认返回Object  但运行类型是Monster
        Monster monster01 = (Monster)ioc.getBean("monster01");

        System.out.println("monster01=" + monster01);
        System.out.println("运行类型" + monster01.getClass());

        //获取时直接指定Class类型
        Monster monster011 = ioc.getBean("monster01", Monster.class);

        System.out.println("monster011" + monster011);

        //查看容器注入了哪些bean对象 输出备案的id
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName=" + beanDefinitionName);
        }
    }

    //验证类加载路径
    @Test
    public void classPath(){
        File file = new File(this.getClass().getResource("/").getPath());
        //类的加载路径
        System.out.println("file=" + file);
    }
}
