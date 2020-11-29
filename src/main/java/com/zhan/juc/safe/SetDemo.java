package com.zhan.juc.safe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author Zhanzhan
 * @Date 2020/11/29 16:07
 * 并发下HashSet安全吗？如果不安全，有什么解决方案？
 */
public class SetDemo {

    public static void main(String[] args) {
        SetDemo setDemo = new SetDemo();
        // 测试普通的HashSet是否线程安全
//        setDemo.testHashSet();

        /**
         * 解决方案
         * 1、使用 Collections 工具类创建
         * 2、使用 CopyOnWriteArraySet 来创建
         */
//        setDemo.testHashSetByCollections();

        setDemo.testHashSetByCopyOnWrite();
    }

    public void testHashSetByCopyOnWrite(){
        Set<String> set = new CopyOnWriteArraySet<>();
        testSafe(set);
    }

    /**
     * 测试普通的 HashSet
     */
    public void testHashSet() {
        testSafe(new HashSet<>());
    }

    public void testHashSetByCollections(){
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        testSafe(set);
    }

    /**
     * 测试当前的 set 集合是否为线程安全
     *
     * @param set
     */
    public void testSafe(Set<String> set) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                set.add(String.valueOf(System.currentTimeMillis()));
                System.out.println(set);
            }, String.valueOf(i + 1)).start();
        }
    }
}
