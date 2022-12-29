package com.hspedu.spring.hspapplicationContext;

import com.hspedu.spring.bean.Monster;
import org.dom4j.DocumentException;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class HspApplicationContextTest {
    public static void main(String[] args) throws Exception {
        HspApplicationContext ioc =
                new HspApplicationContext("beans.xml");

        Monster monster01 = (Monster)ioc.getBean("monster01");
        System.out.println("ok" + "MONSTER01 =" + monster01);
    }
}
