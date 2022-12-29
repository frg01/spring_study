package com.hspedu.spring.ioc;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */

import com.hspedu.spring.annotation.ComponentScan;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 配置类  作用类似原生spring的beans.xml
 */
@ComponentScan(value = "com.hspedu.spring.component")
public class FgrSpringConfig {
}

