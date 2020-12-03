package com.zhan.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author Zhanzhan
 * @Date 2020/12/3 21:07
 * 读写锁
 * 读 - 读   线程可以共存
 * 读 - 写   线程互斥，不能共存
 * 写 - 写   线程互斥，不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 写入操作
        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.put((temp + 1) + "", "value");
            }, String.valueOf(i + 1)).start();
        }

        for (int i = 0; i < 5; i++) {
            int temp = i;
            new Thread(() -> {
                myCache.get((temp + 1) + "");
            }, String.valueOf(i + 1)).start();
        }
    }
}

/**
 * 自定义一个缓存类
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock(); // 加 写锁
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + " 写入" + key);
            map.put(key, value);
            System.out.println("线程 " + Thread.currentThread().getName() + " 写入完成！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();// 释放 写锁
        }
    }

    /**
     * 从缓存中读取数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        readWriteLock.readLock().lock();// 加 读锁
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + " 读取" + key);
            Object o = map.get(key);
            System.out.println("线程 " + Thread.currentThread().getName() + " 读取完成！");
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            readWriteLock.readLock().unlock();// 释放读锁
        }
    }
}
