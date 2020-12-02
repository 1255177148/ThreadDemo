package com.zhan.juc.help;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author Zhanzhan
 * @Date 2020/12/2 21:20
 * 线程加法计数器
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        /**
         * 集齐七颗龙珠就能成功召唤神龙
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！");
        });

        for (int i = 0; i < 7; i++) {
            int temp = i + 1;// lambda 不能直接操作变量 i，所以需要复制一个局部变量
            new Thread(() -> {
                System.out.println("线程" + Thread.currentThread().getName() + " 收集到" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await(); // 这里直接调用await()就行，内部最自动检测是否线程数量达到设置的阈值
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i + 1)).start();
        }
    }
}
