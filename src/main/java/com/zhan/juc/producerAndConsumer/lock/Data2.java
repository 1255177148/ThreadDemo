package com.zhan.juc.producerAndConsumer.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 14:27
 */
public class Data2 {
    private int number = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    /**
     * 增量方法，number + 1
     */
    public void increment() {
        lock.lock();// 加锁
        try {
            while (number != 0){
                // 等待
                condition.await();
            }
            // 开始具体的业务逻辑
            number++;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            // 加完后，通知其他线程 +1 操作完毕了
            condition.signalAll();// 唤醒全部等待线程
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();// 释放锁
        }
    }

    /**
     * 减量操作，number - 1
     */
    public void decrement() {
        lock.lock();// 加锁
        try {
            while (number == 0){
                // 等待
                condition.await();
            }
            // 开始具体的业务逻辑
            number--;
            System.out.println(Thread.currentThread().getName() + "=>" + number);
            // 减完后，通知其他线程 -1 操作完毕了
            condition.signalAll();// 唤醒全部等待线程
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();// 释放锁
        }
    }
}
