package com.zhan.juc.lockBy8question;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 20:40
 * 锁的八个问题--第四个
 * 两个线程 A 和 B ，分别调用同一个资源类的 两个 对象的同步方法， 是否会互相影响
 */
public class Question4 {
    public static void main(String[] args) throws InterruptedException {
        Data data1 = new Data();
        Data data2 = new Data();

        new Thread(() -> {
            data1.printA();
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            data2.printB();
        }, "B").start();
    }
}
