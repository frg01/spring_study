package com.hspedu.spring.taobao.service;

import com.hspedu.spring.taobao.service.BuyGoodService;
import com.hspedu.spring.taobao.service.BuyGoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Service
public class MultiplyService {

    @Resource
    private BuyGoodService goodsService;


    @Transactional(propagation = Propagation.REQUIRED)
    public void ExchangeTx(){
        Float price = goodsService.buyGoods(1, 1, 1);

        Float taobaoProfit = price*0.1f;
        Float sellerProfit = price*0.9f;

        goodsService.updateAccount(0,1,taobaoProfit,sellerProfit);
        System.out.println("商家与平台分帐成功");

    }
}
