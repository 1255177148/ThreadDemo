package com.zhan.juc.forkjoin;

import java.util.OptionalInt;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

/**
 * @Author Zhanzhan
 * @Date 2020/12/21 20:54
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        test2();
        test3();
    }

    /**
     * 用for循环 来计算
     */
    public static void test1() {
        int sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 1000000000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "，耗费的时间为：" + (end - start) + "毫秒");
    }

    /**
     * 使用 fork/join 来计算
     */
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new ForkJoinDemo(0, 1000000000));
        long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "，耗费的时间为：" + (end - start) + "毫秒");
    }

    /**
     * 使用 stream 并行流
     */
    public static void test3() {
        long start = System.currentTimeMillis();
        OptionalInt result = IntStream
                .rangeClosed(0, 1000000000)
                .parallel()
                .reduce(Integer::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum=" + result.getAsInt() + "，耗费的时间为：" + (end - start) + "毫秒");
    }
}
