package com.hspedu.spring.dao;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * @author: guorui fu
 * @versiion: 1.0
Service类
 */
public class OrderService {
    //属性
    private OrderDao orderDao;

    public OrderDao getOrderDao(){
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao){
        this.orderDao = orderDao;
    }
}
