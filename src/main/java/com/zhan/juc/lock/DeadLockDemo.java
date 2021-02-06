package com.zhan.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁demo
 * @Author Zhanzhan
 * @Date 2021/2/6 15:10
 */
public class DeadLockDemo {

    public static void main(String[] args){
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new MyThread(lockA, lockB), "线程1").start();
        new Thread(new MyThread(lockB, lockA), "线程2").start();
    }

    static class MyThread implements Runnable{

        public MyThread(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        private String lockA;
        private String lockB;

        @Override
        public void run() {
            synchronized (lockA){
                System.out.println("线程" + Thread.currentThread().getName() + "的锁:" + lockA + "想要获取锁->" + lockB);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB){
                    System.out.println("线程" + Thread.currentThread().getName() + "的锁:" + lockB + "想要获取锁->" + lockA);
                }
            }
        }
    }
}
