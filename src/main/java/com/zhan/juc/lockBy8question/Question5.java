package com.zhan.juc.lockBy8question;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 20:54
 * 锁的八个问题--第四个
 * 同一个资源类中，设置两个静态的同步方法a() 和 b()，然后用线程A调用a()，线程B调用b()，其中a() 中休眠3秒，问，哪个线程先执行
 */
public class Question5 {
    public static void main(String[]args) throws InterruptedException {

        new Thread(()->{
            Data5.printA();
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            Data5.printB();
        },"B").start();
    }
}

/**
 * 资源类，里面有两个静态同步方法
 */
class Data5{
    public static synchronized void printA(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行线程A");
    }

    public static synchronized void printB(){
        System.out.println("执行线程B");
    }
}
