package com.zhan.juc.function;

import java.util.function.Predicate;

/**
 * @Author Zhanzhan
 * @Date 2020/12/19 15:22
 * 断定式接口
 * 只有一个输入参数，返回一个boolean值
 */
public class PredicateDemo {

    public static void main(String[] args){

//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };

        // 下面两种写法为 lambda表达式写法

//        Predicate<String> predicate = s -> s.isEmpty();

        Predicate<String> predicate = String::isEmpty;

        System.out.println(predicate.test("l"));
    }
}
