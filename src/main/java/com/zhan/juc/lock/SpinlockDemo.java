package com.zhan.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁demo
 * @Author Zhanzhan
 * @Date 2021/2/6 14:33
 */
public class SpinlockDemo {

    static AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 自定义自旋锁 加锁
     */
    public static void lock(){
        Thread thread = Thread.currentThread(); // 获取当前线程
        System.out.println(thread.getName() + "进行上锁");
        // 这里使用CAS，如果期望线程不是null，就一直循环，直到期望线程为null，就替换为 thread
        while (!atomicReference.compareAndSet(null, thread)){
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + "正在自旋");
        }
    }

    /**
     * 自定义自旋锁 解锁
     */
    public static void unlock(){
        Thread thread = Thread.currentThread(); // 获取当前线程
        atomicReference.compareAndSet(thread, null); // 将期望线程替换为null
        System.out.println(thread.getName() + "解锁");
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 用两个线程模拟，其中一个线程先获取锁，但不释放锁，模拟线程阻塞，
         * 然后观察第二个线程获取锁时的情况
         */
        new Thread(() -> {
            lock(); // 这里只加锁，没有释放锁
        }, "线程1").start();

        TimeUnit.SECONDS.sleep(2L); // 这里保证线程1先拿到锁

        new Thread(() -> {
            lock();
            System.out.println("处理业务");
            unlock();
        }, "线程2").start();
    }
}
