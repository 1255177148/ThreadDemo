package com.zhan.juc.volatiletest.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 单例模式--懒汉
 *
 * @Author Zhanzhan
 * @Date 2021/1/30 13:39
 */
public class LazyMan {

    private static boolean flag = false;

    private volatile static LazyMan lazyMan; // 使用volatile修饰，禁止指令重排

    private LazyMan() {
        synchronized (LazyMan.class){
            if (!flag){
                flag = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏单例");
            }
        }
        System.out.println(Thread.currentThread().getName() + "ok");
    }

    /**
     * 双重检测锁 的 懒汉单例模式  DCL懒汉式
     * @return
     */
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    /**
                     * 1、分配内存空间；
                     * 2、执行构造方法，初始化对象；
                     * 3、把这个对象指向这个空间。
                     */
                    lazyMan = new LazyMan(); // 不是一个原子性操作
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws Exception {
        // 用反射破解单例模式
//        LazyMan instance = LazyMan.getInstance();

        Field flag = LazyMan.class.getDeclaredField("flag");// 假设我们知道了标识符的名称
        flag.setAccessible(true); // 无视私有权限

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null); // 获取构造器
        declaredConstructor.setAccessible(true); // 无视私有构造器
        LazyMan instance2 = declaredConstructor.newInstance(); // 通过反射创建对象

        // 更改标识符
        flag.set(instance2, false);

        LazyMan instance = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }
}
