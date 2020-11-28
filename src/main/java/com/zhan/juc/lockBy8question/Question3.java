package com.zhan.juc.lockBy8question;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 20:35
 * 锁的八个问题--第三个
 * 非同步方法是否会受到对象锁的影响
 */
public class Question3 {
    public static void main(String[]args) throws InterruptedException {
        Data data = new Data();

        // 这里的线程A调用的是data对象的同步方法
        new Thread(()->{
            data.printA();
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        // 这里的线程B调用的是data对象的非同步方法
        new Thread(()->{
            data.hello();
        },"B").start();
    }
}
