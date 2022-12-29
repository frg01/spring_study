package com.hspedu.spring.tx.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Service
public class MultiplyService {
    @Resource
    private GoodsService goodsService;

    /**
     * 有两次购买商品 操作  都使用了声明式事务
     * 2.这两个方法buyGoodsByTx，buygoodsbytx2倒是默认REQUIRED[会当作整体事务管理]
     *子事务的失败会导致父事务的回滚，索要要求子事务都正常完成父事务才正常
     * 3.如果两个方法事务传播属性都修改为REQUIRES_NEW
     * 这时两个方法事务独立，不会相互影响
     */
    @Transactional
    public void multiplyBuyGoodsByTx(){
        goodsService.buyGoodsByTx(1,1,1);
        goodsService.buyGoodsByTx2(1,1,1);
    }
}
