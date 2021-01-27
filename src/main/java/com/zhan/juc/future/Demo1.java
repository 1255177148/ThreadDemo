package com.zhan.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2021/1/27 20:30
 * <p>
 * 异步调用： CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 发起一个请求,没有返回值的异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("异步回调");
        });

        System.out.println("主线程");
        completableFuture.get();

        // 有返回值的 异步回调  supplyAsync
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值的异步回调");
            int i = 10 / 0;
            return 1024;
        });

        /**
         * whenComplete,当编译成功时，即成功回调;
         * exceptionally,当回调发生错误时，产生的失败回调
         */
        System.out.println(
                future.whenComplete((t, u) -> {  // 成功的回调
                    System.out.println("t=" + t);
                    System.out.println("u=" + u);
                }).exceptionally((e) -> {        // 失败时的回调
                    System.out.println(e.getMessage());
                    return 233;
                }).get());
    }
}
