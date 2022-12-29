package com.hspedu.spring.service;

import com.hspedu.spring.dao.MemberDAOImpl;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class MemberServiceImpl {

    private MemberDAOImpl memberDAO;

    public MemberDAOImpl getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAOImpl memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void add(){
        System.out.println("MemberServiceImpl add()方法被调用");
        memberDAO.add();
    }
}
