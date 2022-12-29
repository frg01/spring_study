package com.hspedu.spring.component;

import org.springframework.stereotype.Repository;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 使用 @Repository 标识该类是一个Repository持久化层的类或对象
 * 标记注解后默认首字母小写作为id的值
 * value = "fgrUserDao" 使用指定的fgrUserDao作为UserDao对象的id
 */
@Repository/*(value = "fgrUserDao")*/
public class UserDao {
}
