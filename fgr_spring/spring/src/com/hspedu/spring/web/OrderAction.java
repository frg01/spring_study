package com.hspedu.spring.web;

import com.hspedu.spring.dao.OrderDao;
import com.hspedu.spring.dao.OrderService;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * Servlet 就是Controller
 */
public class OrderAction {
    private OrderService orderService;

    //getter
    public OrderService getOrderService() {
        return orderService;
    }

    //setter
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
