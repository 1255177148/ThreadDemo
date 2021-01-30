package com.zhan.juc.volatiletest.single;

/**
 * 单例模式--饿汉,可能会浪费空间
 * @Author Zhanzhan
 * @Date 2021/1/30 13:37
 */
public class Hungry {

    private final static Hungry HUNGRY = new Hungry();

    private Hungry(){

    }

    public static Hungry getInstance(){
        return HUNGRY;
    }
}
