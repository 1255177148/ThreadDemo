package com.zhan.juc.producerAndConsumer.synchronize;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 13:11
 * 线程之间的通信问题
 * 线程交替执行
 * A线程 和 B线程 操作 同一个变量 num，当 A 将 num+1 时，通知 B 将 num-1
 */
public class Demo1 {
    public static void main(String[]args){
        Data data = new Data();
        new Thread(()->{
            for (int i = 0;i<10;i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0;i<10;i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0;i<10;i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(()->{
            for (int i = 0;i<10;i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
