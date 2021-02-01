package com.zhan.juc.volatiletest.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举实现单例模式
 * @Author Zhanzhan
 * @Date 2021/1/30 14:34
 */
public enum  EnumSingle {

    INSTANCE;

    public void anyMethod(){

    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);

        EnumSingle instance1 = declaredConstructor.newInstance();
        EnumSingle instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
