package com.hspedu.spring.bean;

import java.util.List;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class BookStore {

    private List<String> bookList;

    //无参构造器，没有其他构造器的时候无参构造器默认生效

    public List<String> getBookList() {
        return bookList;
    }

    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "BookStore{" +
                "bookList=" + bookList +
                '}';
    }
}
