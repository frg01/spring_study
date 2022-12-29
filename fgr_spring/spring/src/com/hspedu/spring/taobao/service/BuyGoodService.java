package com.hspedu.spring.taobao.service;

import com.hspedu.spring.taobao.dao.ExchangeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Service
public class BuyGoodService {

    @Resource
    private ExchangeDao exchangeDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Float buyGoods(int userId,int goodsId,int amount){

        //输出购买信息
        System.out.println("用户购买信息 userId=" + userId
                + "goodId=" + goodsId + "amount=" + amount);
        //1.得到商品的价格
        Float price = exchangeDao.queryPriceById(goodsId);
        //1.减少用户余额
        exchangeDao.updateBalance(userId,price*amount);
        //3.减少库存
        exchangeDao.updateAmountStock(goodsId,amount);
        //4.增加销量
        exchangeDao.updateAmountSell(goodsId,amount);
        System.out.println("用户购买成功~");
        return price;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateAccount(int taobaoId,int sellerId ,Float taobaoProfit,Float sellerProfit){

        //1.修改商家余额
        exchangeDao.updateSellAccount(sellerId,sellerProfit);
        //2.修改淘宝余额
        exchangeDao.updateTaobaoAccount(taobaoId,taobaoProfit);
        //修改淘宝收款次数
        exchangeDao.updateTaobaoCount();

        System.out.println("用户购买成功~");
    }
}
