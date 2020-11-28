package com.zhan.juc.lockBy8question;

import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 15:43
 * 资源类
 */
public class Data {

    public synchronized void printA(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行线程A");
    }

    public synchronized void printB(){
        System.out.println("执行线程B");
    }

    public void hello(){
        System.out.println("hello");
    }
}
