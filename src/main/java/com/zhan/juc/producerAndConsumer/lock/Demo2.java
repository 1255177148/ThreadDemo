package com.zhan.juc.producerAndConsumer.lock;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 14:38
 */
public class Demo2 {
    public static void main(String[]args){
        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0;i<10;i++){
                data.increment();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0;i<10;i++){
                data.decrement();
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0;i<10;i++){
                data.increment();
            }
        }, "C").start();

        new Thread(()->{
            for (int i = 0;i<10;i++){
                data.decrement();
            }
        }, "D").start();
    }
}
