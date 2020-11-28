package com.zhan.juc.lockBy8question;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 22:09
 * 线程八锁
 * 同一个资源类中，有一个静态同步方法 a()，和一个普通同步方法 b()，然后用线程A调用a()，线程B调用b()，其中a() 中休眠3秒，哪个线程先执行？
 */
public class Question6 {
    public static void main(String[] args) throws InterruptedException {
        Data6 data = new Data6();

        new Thread(Data6::printA, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            data.printB();
        }, "B").start();
    }
}

class Data6{

    /**
     * 这里锁的是当前的Class对象
     */
    public static synchronized void printA(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行线程A");
    }

    /**
     * 这里锁的是当前的调用者对象，即 new 出来的对象
     */
    public synchronized void printB(){
        System.out.println("执行线程B");
    }
}
