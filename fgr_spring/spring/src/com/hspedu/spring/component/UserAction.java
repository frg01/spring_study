package com.hspedu.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * @Controller 标识该类是一个控制器 Servlet
 */
@Controller
public class UserAction {
//    @Autowired//按照类型匹配

    @Resource(name = "userService")//type = UserService 按照id或类型  不指定则先name后type
    private UserService userService;
    public void sayOk(){
        System.out.println("UserAction 的sayOk方法");
        userService.hi();
    }
}
