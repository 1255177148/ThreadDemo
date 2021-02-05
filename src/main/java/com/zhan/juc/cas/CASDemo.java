package com.zhan.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS  compareAndSet:比较并交换
 * @Author Zhanzhan
 * @Date 2021/2/5 20:35
 */
public class CASDemo {
    public static void main(String[] args){
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        /**
         * 期望、更新
         * 如果我的期望的值达到了，那么就更新，否则，就不更新
         * CAS是 CPU 的并发原语
         */
        System.out.println(atomicInteger.compareAndSet(2020, 2021)); // 如果是期望的值2020，就更新为2021
        System.out.println(atomicInteger.get());

        atomicInteger.getAndIncrement(); // 当前值+1,递增
    }
}
