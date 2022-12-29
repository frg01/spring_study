package com.hspedu.spring.depinjection;

import org.springframework.stereotype.Repository;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
@Repository
public class PhoneDao extends BaseDao<Phone>{
    @Override
    public void save() {
        System.out.println("PhoneDao save()..");
    }
}
