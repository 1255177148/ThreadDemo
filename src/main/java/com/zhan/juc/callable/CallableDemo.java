package com.zhan.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author Zhanzhan
 * @Date 2020/12/1 21:17
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 整体写法
        new Thread(new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        })).start();

        // 分开写，并获取返回结果
        MyThread myThread = new MyThread();
        FutureTask<String> futureTask = new FutureTask<>(myThread);
        new Thread(futureTask, "A").start();
        String value = futureTask.get();
        System.out.println("从Callable中获取到的值为：" + value);
    }
}

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "线程" + Thread.currentThread().getName() + " say->hello world";
    }
}
