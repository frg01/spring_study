package com.hspedu.spring;

import com.hspedu.spring.bean.Monster;
import com.hspedu.spring.jdbcTempalte.MonsterDao;
import com.hspedu.spring.tx.service.GoodsService;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class JdbcTemplateTest {

    @Test
    public void testDataSourceByJdbcTemplate() throws SQLException {
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //这个类实现了DataSource接口，所以可以获取DataSource接口对象
        DataSource dataSource = ioc.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println("connection= " +connection);
        connection.close();
        System.out.println("ok");
    }


    //测试通过JdbcTemplate对象完成添加数据
    @Test
    public void addDataByJdbcTemplate(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //获取JdbcTemplate对象
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
        //添加方式1
//        String sql = "INSERT INTO monster VALUES (400,'红孩儿','枪法')";
//        jdbcTemplate.execute(sql);
        //添加方式2 预处理防止sql注入
        String sql = "INSERT INTO monster VALUES (?,?,?)";
        int update = jdbcTemplate.update(sql, 500, "牛魔王", "变牛");
        System.out.println("添加成功,影响记录数" + update);
    }

    //测试通过JdbcTemplate对象完成修改数据
    @Test
    public void updateDataByJdbcTemplate(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //SQl语句
        String sql = "UPDATE monster SET skill= ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, "头撞墙", 500);

        System.out.println("update 修改成功" + update);
    }

    //批量添加
    /*使用API的技巧
    大量的API，先找到API名字  根据API提供的相应的参数  清晰iji的调用思路
     */
    @Test
    public void addBatchDataByJdbcTemplate(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //SQl语句
        String sql = "INSERT INTO monster VALUES (?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{600,"老鼠精","偷吃食"});
        batchArgs.add(new Object[]{700,"老猫","上树"});
        //猜测api名字  返回sql语句对表的影响的记录数
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        for (int anInt : ints) {
            System.out.println("anInt= " + anInt);
        }
        System.out.println("batch add ok");
    }

    //查询id=100 的monster 并封装到Monster实体对象  实际开发中非常有用
    @Test
    public void selectDataByJdbcTemplate(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //确定API queryForObject()
        //准备参数
        //SQl语句
        String sql = "SELECT id AS monsterId,`name`,skill FROM monster WHERE id = 100;";
        //准备参数 查询的结果封装使用RowMapper接口
        //查询的记录的表的字段需要和Monster字段名保持一致
        RowMapper<Monster> rowMapper = new BeanPropertyRowMapper<>(Monster.class);
        Monster monster = jdbcTemplate.queryForObject(sql, rowMapper);

        System.out.println("monster= " + monster);
    }

    //查询id>100 的monster 并封装到Monster实体对象
    @Test
    public void selectDataByJdbcTemplate2(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //确定API queryForObject()
        //准备参数
        //SQl语句
        String sql = "SELECT id AS monsterId,`name`,skill FROM monster WHERE id > ?;";
        //准备参数 查询的结果封装使用RowMapper接口
        //查询的记录的表的字段需要和Monster字段名保持一致
        RowMapper<Monster> rowMapper = new BeanPropertyRowMapper<>(Monster.class);
        List<Monster> monsterList = jdbcTemplate.query(sql, rowMapper, 100);
        for (Monster monster : monsterList) {
            System.out.println("monster" + monster);
        }

    }


    //查询单行单列的值
    @Test
    public void selectSingleDataByJdbcTemplate(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        //确定API queryForObject()
        //准备参数
        //SQl语句
        String sql = "SELECT `name` FROM monster WHERE id = ?;";

        String name = jdbcTemplate.queryForObject(sql, String.class, 100);

        System.out.println("返回name= " + name);
    }

    //指定参数添加语句
    @Test
    public void testDataByNameParameterJdbcTemplate(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = ioc.getBean(NamedParameterJdbcTemplate.class);
        //确定API queryForObject()
        //准备参数
        //SQl语句
        String sql = "INSERT INTO monster VALUES (:id,:name,:skill)";
        Map<String, Object> paramMap = new HashMap<>();
        //给Map填写数据
        paramMap.put("id",800);
        paramMap.put("name","蚂蚁怪");
        paramMap.put("skill","打洞");
        int update = namedParameterJdbcTemplate.update(sql, paramMap);


        System.out.println("被影响行数= " + update);
    }


    //sqlParameterSource参数的使用
    @Test
    public void operDataBySqlParameterSource(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");

        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = ioc.getBean(NamedParameterJdbcTemplate.class);
        //确定API queryForObject()
        //准备参数
        //SQl语句
        String sql = "INSERT INTO monster VALUES (:monsterId,:name,:skill)";
        Monster monster = new Monster(900, "大象怪", "地鸣");
        BeanPropertySqlParameterSource sqlParameterSource
                = new BeanPropertySqlParameterSource(monster);
        //调用
        int update
                = namedParameterJdbcTemplate.update(sql, sqlParameterSource);

        System.out.println("被影响行数= " + update);
    }

    //测试monsterDao
    @Test
    public void monsterDaoSave(){
        ApplicationContext ioc
                = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        MonsterDao monsterDao = ioc.getBean(MonsterDao.class);
        Monster monster = new Monster(1000, "小鸭子", "吃鱼");
        monsterDao.save(monster);
        System.out.println("保存成功");
    }


}
