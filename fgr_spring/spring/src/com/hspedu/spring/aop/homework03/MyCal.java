package com.hspedu.spring.aop.homework03;

import org.springframework.stereotype.Component;



/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Component
public class MyCal implements Cal{
    @Override
    public Integer cal1(int n) {

        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        System.out.println("cal1() res=" + result);

        return result;
    }

    @Override
    public Integer cal2(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        System.out.println("cal2() res=" + result);
        return result;
    }
}
