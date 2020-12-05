package com.zhan.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Zhanzhan
 * @Date 2020/12/5 16:34
 * Executors工具类  -- 三大方法
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// 创建一个固定大小的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();// 创建一个可伸缩的线程池

        try {
            for (int i = 0; i < 10; i++) {
                // 使用了线程池后，就用线程池来创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 创建");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，要关闭线程池
            threadPool.shutdown();
        }
    }
}
