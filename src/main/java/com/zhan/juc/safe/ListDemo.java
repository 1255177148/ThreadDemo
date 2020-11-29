package com.zhan.juc.safe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author Zhanzhan
 * @Date 2020/11/29 15:06
 * 并发下ArrayList安全吗？如果不安全，有什么解决方案？
 */
public class ListDemo {

    public static void main(String[]args){
        ListDemo listDemo = new ListDemo();
        // 测试普通的ArrayList是否为线程安全
//        listDemo.testArrayList();

        /**
         * 解决方案
         * 1、使用 Collections 工具类创建
         * 2、使用 CopyOnWriteArrayList 来创建
         */
//        listDemo.testArrayListByCollections();

        listDemo.testArrayListByCopyOnWrite();
    }

    /**
     * <p>使用 CopyOnWriteArrayList 来创建。</p>
     * <p>CopyOnWrite -- 写入时复制 ，是 COW 思想，就是计算机程序设计领域的一种优化策略。</p>
     * <p>多个线程调用的时候，写或者添加元素时，会 copy 一份副本出来</p>
     *
     */
    public void testArrayListByCopyOnWrite(){
        List<String> list = new CopyOnWriteArrayList<>();
        testSafe(list);
    }

    /**
     * 使用Collections工具类创建一个同步List
     */
    public void testArrayListByCollections(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        testSafe(list);
    }

    /**
     * 测试普通的ArrayList
     */
    public void testArrayList(){
        testSafe(new ArrayList<>());
    }

    /**
     * 测试当前的List集合是否线程安全
     * @param list
     */
    public void testSafe(List<String> list){
        for (int i = 0;i<10;i++){
            new Thread(()->{
                list.add(String.valueOf(System.currentTimeMillis()));
                System.out.println(list);
            }, String.valueOf(i + 1)).start();
        }
    }
}
