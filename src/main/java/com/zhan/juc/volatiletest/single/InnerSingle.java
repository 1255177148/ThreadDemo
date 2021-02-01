package com.zhan.juc.volatiletest.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 静态内部类实现单例模式
 *
 * 用一个静态内部类来持有外部类的对象实例，
 * 外部类加载时不需要立即加载内部类，只有当调用getInstance()时，
 * 才会加载InnerSingleHolder类，这样不仅能保证线程安全，也能保证单例的唯一性。
 *
 * <p>虽说如此，但是依然能用反射来破解此单例模式</p>
 * @Date 2021/2/1 11:42
 * @Author hezhan
 */
public class InnerSingle {

    private InnerSingle(){

    }

    public static InnerSingle getInstance(){
        return InnerSingleHolder.SINGLE;
    }

    private static class InnerSingleHolder{
        final static InnerSingle SINGLE = new InnerSingle();
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        InnerSingle instance1 = getInstance();
        Constructor<InnerSingle> declaredConstructor = InnerSingle.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        InnerSingle instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
