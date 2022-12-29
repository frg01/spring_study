package com.hspedu.spring.tx;

import com.hspedu.spring.tx.dao.GoodsDao;
import com.hspedu.spring.tx.service.GoodsService;
import com.hspedu.spring.tx.service.MultiplyService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class TxTest {

    @Test
    public void queryPriceByIdTest(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        Float price = goodsDao.queryPriceById(1);
        System.out.println("id=1的商品价格 = " + price);
    }

    @Test
    public void updateBalance(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        goodsDao.updateBalance(1,1.0f);

        System.out.println("减少用户余额成功");
    }

    @Test
    public void updateAmount(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsDao goodsDao = ioc.getBean(GoodsDao.class);
        goodsDao.updateAmount(1,1);

        System.out.println("减少库存成功");
    }
    //测试用户购买商品业务
    @Test
    public void buyGoodsTest(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);
        goodsService.buyGoods(1,1,1);
        System.out.println("购买商品成功~");
    }

    //测试事务的传播机制
    @Test
    public void multiBuyGoodsByTxTest(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("tx_ioc.xml");

        MultiplyService multiplyService = ioc.getBean(MultiplyService.class);
        multiplyService.multiplyBuyGoodsByTx();
        System.out.println("购买商品成功~");
    }

    //测试声明式事务的隔离级别
    @Test
    public void buyGoodsByTxISOLATIONTest(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);
        goodsService.buyGoodsByTxISOLATION();
    }

    //测试timeout
    @Test
    public void buyGoodsByTxTimeTest(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("tx_ioc.xml");

        GoodsService goodsService = ioc.getBean(GoodsService.class);
        goodsService.buyGoodsByTxTimeout(1,1,1);
    }
}
