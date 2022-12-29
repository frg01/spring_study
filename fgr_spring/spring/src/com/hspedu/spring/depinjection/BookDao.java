package com.hspedu.spring.depinjection;

import org.springframework.stereotype.Repository;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Repository
public class BookDao extends BaseDao<Book>{
    @Override
    public void save() {
        System.out.println("BookDao 的save()..");
    }
}
