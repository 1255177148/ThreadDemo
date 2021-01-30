package com.zhan.juc.volatiletest.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举实现单例模式
 * @Author Zhanzhan
 * @Date 2021/1/30 14:34
 */
public class EnumSingle {

    enum EnumTest{
        INSTANCE;

        private EnumSingle enumSingle = null;

        private EnumTest(){
            enumSingle = new EnumSingle();
        }

        public EnumSingle getEnumSingle(){
            return enumSingle;
        }
    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<EnumTest> declaredConstructor = EnumTest.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);

        EnumTest instance1 = declaredConstructor.newInstance();
        EnumTest instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
