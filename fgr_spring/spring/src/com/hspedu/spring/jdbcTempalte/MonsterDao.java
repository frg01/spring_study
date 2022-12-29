package com.hspedu.spring.jdbcTempalte;

import com.hspedu.spring.bean.Monster;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Repository  //将MonsterDao 注入到spring容器
public class MonsterDao {

    //注入属性
    @Resource
    private JdbcTemplate jdbcTemplate;
    //完成保存任务
    public void save(Monster monster) {
        //组织sql语句
        String sql = "INSERT INTO monster VALUES(?,?,?)";
        int update
                = jdbcTemplate.update(sql, monster.getMonsterId(), monster.getName(), monster.getSkill());

    }
}
