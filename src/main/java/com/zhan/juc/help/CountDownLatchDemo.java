package com.zhan.juc.help;

import java.util.concurrent.CountDownLatch;

/**
 * @Author Zhanzhan
 * @Date 2020/12/2 21:06
 * 线程计数器
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 初始化一个计数器，总数为6
        CountDownLatch count = new CountDownLatch(6);

        // 开启多个线程
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getName() + " 运行完毕");
                count.countDown(); // 计数器 -1
            }, String.valueOf(i + 1)).start();
        }
        count.await(); // 阻塞当前主线程，直到开启的那些线程全部执行完毕，也就是计数器归零
        System.out.println("运行完毕");
    }
}
