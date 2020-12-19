package com.zhan.juc.function;

import java.util.function.Supplier;

/**
 * @Author Zhanzhan
 * @Date 2020/12/19 15:51
 * 供给型函数
 * 没有参数，只有返回值
 */
public class SupplierDemo {

    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "hello world";
//            }
//        };

        // 下面两种是 lambda 表达式 写法
//        Supplier<String> supplier = () -> {return "hello world";};

        Supplier<String> supplier = () -> "hello world";

        System.out.println(supplier.get());
    }
}
