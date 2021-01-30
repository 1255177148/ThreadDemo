package com.zhan.juc.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试volatile不保证原子性
 *
 * @Author Zhanzhan
 * @Date 2021/1/28 21:19
 */
public class Demo {
//    private volatile static int num = 0;

    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
//        num++; // 不是一个原子性操作
        num.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        // 判断当前的线程是否大于两条
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " num=" + num);
    }
}
