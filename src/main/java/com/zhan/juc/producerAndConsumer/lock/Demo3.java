package com.zhan.juc.producerAndConsumer.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 14:46
 * 实现多个线程间的精确唤醒和通知
 * A 执行完 调用 B， B 执行完 调用 C， C 执行完 调用 A
 */
public class Demo3 {
    public static void main(String[]args){
        Data3 data = new Data3();

        new Thread(()->{
            for (int i = 0;i < 10;i++){
                data.printA();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0;i < 10;i++){
                data.printB();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0;i < 10;i++){
                data.printC();
            }
        },"C").start();
    }
}

/**
 * 资源类
 */
class Data3{
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();
    private int number = 1;// 1 -> A, 2 -> B, 3 -> C

    /**
     * A线程执行
     */
    public void printA(){
        lock.lock();
        try {
            // 业务， 判断 -> 执行 -> 通知
            while (number != 1){
                // 等待
                condition1.await();// 将同步代码块 printA() 关入 小黑屋 condition1
            }
            System.out.println(Thread.currentThread().getName()+ "运行---");
            number = 2;
            // 通知 指定的线程
            condition2.signal();// 小黑屋 condition2 开门，放出里面关的
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * B线程执行
     */
    public void printB(){
        lock.lock();
        try {
            // 业务， 判断 -> 执行 -> 通知
            while (number != 2){
                // 等待
                condition2.await();// 将同步代码块 printB() 关入 小黑屋 condition2
            }
            System.out.println(Thread.currentThread().getName()+ "运行---");
            number = 3;
            // 通知 指定的线程
            condition3.signal();// 小黑屋 condition3 开门，放出里面关的
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * C线程执行
     */
    public void printC(){
        lock.lock();
        try {
            // 业务， 判断 -> 执行 -> 通知
            while (number != 3){
                // 等待
                condition3.await();// 将同步代码块 printC() 关入 小黑屋 condition3
            }
            System.out.println(Thread.currentThread().getName()+ "运行---");
            number = 1;
            // 通知 指定的线程
            condition1.signal();// 小黑屋 condition1 开门，放出里面关的
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
