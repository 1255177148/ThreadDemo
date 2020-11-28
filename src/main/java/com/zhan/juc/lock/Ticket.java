package com.zhan.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Zhanzhan
 * @Date 2020/11/27 21:15
 */
public class Ticket {
    private int number = 50;

    Lock lock = new ReentrantLock();

    /**
     * 使用synchronized关键字来加锁，保证运行顺序
     */
    public synchronized void saleBySynchronized() {
        if (number > 0) {
            number--;
            System.out.println(Thread.currentThread().getName() + "卖出了第" + (50 - number) + "张票，剩余" + number + "张票");
        }
    }

    /**
     * 使用lock来加锁，保证运行顺序
     */
    public void saleByLock() {
        lock.lock();// 加锁
        try {
            // 业务代码
            if (number > 0) {
                number--;
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (50 - number) + "张票，剩余" + number + "张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();// 释放锁
        }
    }
}
