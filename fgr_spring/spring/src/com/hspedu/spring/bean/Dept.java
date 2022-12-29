package com.hspedu.spring.bean;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class Dept {
    private String name;

    public Dept() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
