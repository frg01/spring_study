package com.hspedu.spring.tx.service;

import com.hspedu.spring.tx.dao.GoodsDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Service//将GoodsService注入到spring容器
public class GoodsService {

    //定义属性GoodsDao
    @Resource
    private GoodsDao goodsDao;
    //编写用户购买商品的业务

    /**
     *
     * @param userId 用户id
     * @param goodsId 商品id
     * @param amount 购买数量
     * 主要验证业务
     */
    public void buyGoods(int userId,int goodsId,int amount){

        //输出购买信息
        System.out.println("用户购买信息 userId=" + userId
                + "goodId=" + goodsId + "amount=" + amount);
        //1.得到商品的价格
        Float price = goodsDao.queryPriceById(goodsId);
        //2.减少用户余额
        goodsDao.updateBalance(userId,price*amount);
        //3.减少库存
        goodsDao.updateAmount(goodsId,amount);

        System.out.println("用户购买成功~");
    }


    /**
     *@Transactional  注解解读 可以进行声明式食物控制 将标识的方法中对数据库的操作作为一个整体
     * @param userId 用户id
     * @param goodsId 商品id
     * @param amount 购买数量
     * @Transactional 底层动态代理对象来调用buyGoodsByTx
     * buyGoodsByTx执行此方法之前 先调用事务管理器的doBegin() ,在开始执行方法
     * 如果过程中没有发生异常 调用事务管理器的doCommit() 发生异常则调用事务管理器的doRollBack()
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyGoodsByTx(int userId,int goodsId,int amount){

        //输出购买信息
        System.out.println("用户购买信息 userId=" + userId
                + "goodId=" + goodsId + "amount=" + amount);
        //1.得到商品的价格
        Float price = goodsDao.queryPriceById(goodsId);
        //2.减少用户余额
        goodsDao.updateBalance(userId,price*amount);
        //3.减少库存
        goodsDao.updateAmount(goodsId,amount);

        System.out.println("用户购买成功~");
    }

    //第二套商品购买的方法
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void buyGoodsByTx2(int userId,int goodsId,int amount){

        //输出购买信息
        System.out.println("用户购买信息 userId=" + userId
                + "goodId=" + goodsId + "amount=" + amount);
        //1.得到商品的价格
        Float price = goodsDao.queryPriceById2(goodsId);
        //2.减少用户余额
        goodsDao.updateBalance2(userId,price*amount);
        //3.减少库存
        goodsDao.updateAmount2(goodsId,amount);

        System.out.println("用户购买成功~");
    }

    /**
     * 默认下  声明式事务的隔离级别是 REPEATABLE_READ
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void buyGoodsByTxISOLATION(){
        //查询两次商品
        Float price = goodsDao.queryPriceById(1);
        System.out.println("1价格= " + price);

        Float price2 = goodsDao.queryPriceById(1);
        System.out.println("2价格= " + price2);
    }

    //@Transactional(timeout = 2) 此事务方法执行时间超过两秒，就回滚
    //-1 默认超时时间
    @Transactional(timeout = 2)
    public void buyGoodsByTxTimeout(int userId,int goodsId,int amount){


        //输出购买信息
        System.out.println("用户购买信息 userId=" + userId
                + "goodId=" + goodsId + "amount=" + amount);
        //1.得到商品的价格
        Float price = goodsDao.queryPriceById2(goodsId);
        //2.减少用户余额
        goodsDao.updateBalance2(userId,price*amount);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //3.减少库存
        goodsDao.updateAmount2(goodsId,amount);

        System.out.println("用户购买成功~");
    }
}
