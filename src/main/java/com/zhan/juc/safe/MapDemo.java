package com.zhan.juc.safe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Zhanzhan
 * @Date 2020/11/29 16:31
 * 并发下HashMap安全吗？如果不安全，有什么解决方案？
 */
public class MapDemo {

    public static void main(String[] args) {
        MapDemo mapDemo = new MapDemo();

        // 测试普通的 HashMap 是否为线程安全
//        mapDemo.testHashMap();

        /**
         * 解决方案
         * 1、使用 Collections 工具类创建
         * 2、使用 ConcurrentHashMap
         */
//        mapDemo.testHashMapByCollections();

        mapDemo.testHashMapByConcurrent();
    }

    /**
     * 使用ConcurrentHashMap来创建线程安全的 HashMap
     */
    public void testHashMapByConcurrent() {
        Map<String, String> map = new ConcurrentHashMap<>();
        testSafe(map);
    }

    /**
     * 使用 Collections 来创建 同步的HashMap
     */
    public void testHashMapByCollections() {
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        testSafe(map);
    }

    public void testHashMap() {
        testSafe(new HashMap<>());
    }

    public void testSafe(Map<String, String> map) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                map.put(String.valueOf(System.currentTimeMillis()), "");
                System.out.println(map);
            }, String.valueOf(i + 1)).start();
        }
    }
}
