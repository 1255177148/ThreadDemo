package com.zhan.juc.function;

import java.util.function.Consumer;

/**
 * @Author Zhanzhan
 * @Date 2020/12/19 15:33
 * 消费性接口
 * 只有输入，没有返回值
 */
public class ConsumerDemo {

    public static void main(String[] args){
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };

        // 下面两种都是 lambda 表达式 写法

//        Consumer<String> consumer = s -> System.out.println(s);

        Consumer<String> consumer = System.out::println;

        consumer.accept("hello world");
    }
}
