package com.zhan.juc.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/12/5 17:08
 * 七大参数、四种拒绝策略
 */
public class ThreadPoolExecutorsDemo {
    public static void main(String[] args) {

        // 获取CPU的核数
        System.out.println("CPU的核数为: " + Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,  // 核心线程数
                5,  // 最大核心线程数
                3, // 超时了没有人调用就会释放，指定超时时间
                TimeUnit.SECONDS, // 超时单位
                new LinkedBlockingQueue<>(3), // 阻塞队列，这里指定阻塞队列的容量为3
                Executors.defaultThreadFactory(), // 线程工厂，一般不用动, 就使用 Executors.defaultThreadFactory()
                new ThreadPoolExecutor.DiscardPolicy()); // 拒绝策略

        try {
            // 线程池的最大承载量 = 最大核心线程数 + 阻塞队列容量
            for (int i = 0; i < 9; i++) {
                // 使用了线程池后，就用线程池来创建线程
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 创建");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，要关闭线程池
            threadPoolExecutor.shutdown();
        }
    }
}
