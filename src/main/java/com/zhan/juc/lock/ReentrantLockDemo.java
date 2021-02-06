package com.zhan.juc.lock;

/**
 * 可重入锁demo
 * @Author Zhanzhan
 * @Date 2021/2/6 13:59
 */
public class ReentrantLockDemo {

    static MyLock myLock = new MyLock(); // 不可重入锁对象

    static MyReentrantLock myReentrantLock = new MyReentrantLock(); // 可重入锁对象

    public static void main(String[] args) throws InterruptedException {
        testMyReentrantLock();
    }

    /**
     * 测试不可重入锁，即在单个线程内，多次上锁，看是否可以，还是会阻塞线程
     * @throws InterruptedException
     */
    public static void testMyLock1() throws InterruptedException {
        myLock.lock(); // 第一次上锁
        System.out.println("执行testMyLock2()前---");
        testMyLock2(); // 这个方法内再次上锁
        myLock.unlock();
    }

    public static void testMyLock2() throws InterruptedException {
        myLock.lock();
        System.out.println("执行testMyLock2()中---");
        myLock.unlock();
    }

    /**
     * 测试可重入锁，即在单个线程内，可以多次上锁
     * @throws InterruptedException
     */
    public static void testMyReentrantLock() throws InterruptedException {
        myReentrantLock.lock(); // 上锁
        System.out.println("testMyReentrantLock2()前---");
        testMyReentrantLock2(); // 这个方法内再次上锁
        myReentrantLock.unlock();
    }

    public static void testMyReentrantLock2() throws InterruptedException {
        myReentrantLock.lock();
        System.out.println("执行testMyReentrantLock2()中---");
        myReentrantLock.unlock();
    }

    /**
     * 自定义一个可重入锁
     */
    static class MyReentrantLock{
        private boolean isLocked = false;
        private Thread lockedBy = null; // 用来记录要上锁的线程
        private int lockedCount = 0; // 上锁次数计数

        /**
         * 上锁
         * @throws InterruptedException
         */
        public synchronized void lock() throws InterruptedException {
            // 获取当前线程
            Thread thread = Thread.currentThread();
            // 如果上锁了，并且不是同一个线程，或者不是目标线程，就会进入循环等待
            while (isLocked && lockedBy != thread){
                wait(); // 阻塞非目标线程
            }
            isLocked = true; // 进行上锁
            lockedCount++; // 上锁次数+1
            lockedBy = thread; // 当前要上锁的目标线程
        }

        /**
         * 解锁
         */
        public synchronized void unlock(){
            // 如果是目标线程，就开始释放锁
            if (Thread.currentThread() == lockedBy){
                lockedCount--; // 将上锁次数 -1
                // 当上锁计数为 0，则说明目标线程已经释放了所有的锁
                if (lockedCount == 0){
                    isLocked = false; // 将上锁的标识设置为 false，真正的释放了目标线程所有重入的锁
                    notify();
                }
            }
        }
    }

    /**
     * 自定义一个不可重入锁
     */
    static class MyLock{
        private boolean isLocked = false;

        /**
         * 加锁
         * @throws InterruptedException
         */
        public synchronized void lock() throws InterruptedException {
            while (isLocked){
                wait();
            }
            isLocked = true; // 线程第一次进入后就会将标识设置为true，第二次进入时就会因为while(true)进入死循环
        }

        /**
         * 解锁
         */
        public synchronized void unlock(){
            isLocked = false; // 将这个值设置为false，目的就是释放锁
            notify();
        }
    }
}
