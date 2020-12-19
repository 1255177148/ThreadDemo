package com.zhan.juc.function;

import java.util.function.Function;

/**
 * @Author Zhanzhan
 * @Date 2020/12/19 15:16
 * 函数式接口
 * 有一个输入参数，一个输出
 */
public class FunctionDemo {
    public static void main(String[] args) {

//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };

        // 上面的可以用这个  lambda表达式简化为下面的这种
        Function<String, String> function = str -> str;

        System.out.println(function.apply("hello"));
    }
}
