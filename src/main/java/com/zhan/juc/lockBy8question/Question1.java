package com.zhan.juc.lockBy8question;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 15:42
 * 锁的八个问题--第一个
 * 两个线程，分别调用同一个资源类的两个方法，哪个先执行
 */
public class Question1 {
    public static void main(String[]args) throws InterruptedException {
        Data data = new Data();

        //
        new Thread(()->{
            data.printA();
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            data.printB();
        },"B").start();
    }
}
