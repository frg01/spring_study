package com.hspedu.spring.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 就是一个Controller
 */
//默认情况下  配置注解都是单例的
@Component
@Scope(value = "prototype")
public class UserAction {
}
