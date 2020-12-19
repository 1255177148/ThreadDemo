package com.zhan.juc.stream;

import lombok.Data;

/**
 * @Author Zhanzhan
 * @Date 2020/12/19 15:59
 */
@Data
public class User {
    private int id;
    private String name;
    private int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
