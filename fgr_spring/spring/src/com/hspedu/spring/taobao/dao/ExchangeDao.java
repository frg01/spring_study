package com.hspedu.spring.taobao.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Repository
public class ExchangeDao {

    @Resource
    JdbcTemplate jdbcTemplate;

    /**
     * 根据商品id，返回对应的价格
     * @param id
     * @return
     */
    public Float queryPriceById(Integer id) {
        String sql = "SELECT price From goods_list Where id=?";
        Float price = jdbcTemplate.queryForObject(sql, Float.class, id);
        return price;
    }

    /**
     * 修改用户的余额
     * @param user_id
     * @param money
     */
    public void updateBalance(Integer user_id, Float money) {
        String sql = "UPDATE buyers SET money=money-? Where id=?";
        jdbcTemplate.update(sql, money, user_id);
    }

    /**
     * 修改商品库存
     * @param goods_id
     * @param amount
     */
    public void updateAmountStock(Integer goods_id, int amount){
        String sql = "UPDATE goods_list SET stock=stock-? Where id=?";
        jdbcTemplate.update(sql, amount , goods_id);
    }

    /**
     * 修改商品库存
     * @param goods_id
     * @param amount
     */
    public void updateAmountSell(Integer goods_id, int amount){
        String sql = "UPDATE goods_list SET sell=sell+?  WHERE id=?";
        jdbcTemplate.update(sql, amount , goods_id);
    }

    //修改商家账户余额
    public void updateSellAccount(Integer goods_id, Float profit){
        String sql = "UPDATE seller SET account=account+?  WHERE id=?";
        jdbcTemplate.update(sql, profit , goods_id);
    }
    //修改淘宝平台余额
    public void updateTaobaoAccount(Integer goods_id, Float profit){
        String sql = "UPDATE taobao_account SET account=account+?  WHERE id=?";
        jdbcTemplate.update(sql, profit , goods_id);
    }

    //修改淘宝平台打款次数
    public void updateTaobaoCount(){
        String sql = "UPDATE taobao_account SET reserve_count=reserve_count+1  WHERE id=0";
        jdbcTemplate.update(sql);
    }
}
