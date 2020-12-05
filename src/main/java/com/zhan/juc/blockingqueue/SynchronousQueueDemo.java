package com.zhan.juc.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/12/5 15:36
 * 同步队列
 * 和其他的BlockingQueue 不一样， SynchronousQueue 不存储元素,
 * 当生产者 往 队列中生产添加了一个数据，必须等到有消费者 从队列中取出消费了 这个数据后，此队列才能再继续添加 元素
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println("队列 " + Thread.currentThread().getName() + "生产了一个数据：1");
                blockingQueue.put("1");

                System.out.println("队列 " + Thread.currentThread().getName() + "生产了一个数据：2");
                blockingQueue.put("2");

                System.out.println("队列 " + Thread.currentThread().getName() + "生产了一个数据：3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程 " + Thread.currentThread().getName() + " 消费了一个数据：" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程 " + Thread.currentThread().getName() + " 消费了一个数据：" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程 " + Thread.currentThread().getName() + " 消费了一个数据：" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费者").start();
    }
}
