package com.zhan.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p>CAS机制引起的ABA问题。</p>
 * <p>这里我们用两个线程，然后线程1先执行，将2020改为2021，然后又改回2020</p>
 * <p>然后线程2执行完计算，将计算后的值与主存中的值对比，发现是期望值，就执行了CAS操作，更新了期望值</p>
 * <p>我们原本希望的是，主存的值被改动过，其他线程需要得知这个情况</p>
 * @Author Zhanzhan
 * @Date 2021/2/5 21:48
 */
public class ABADemo {
    static AtomicReference<String> atomicReference = new AtomicReference<>("2020");

    static AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("2020", 1);// 这里是带版本号的原子类

    public static void main(String[] args) throws InterruptedException {

        System.out.println("======= ABA 问题 ========");

        new Thread(() -> {
            System.out.println("将原始值改为 2021");
            atomicReference.compareAndSet("2020", "2021");
            System.out.println(atomicReference.get());
            System.out.println("哎嘿，我又改回来了-_-");
            atomicReference.compareAndSet("2021", "2020");
            System.out.println(atomicReference.get());
        }, "线程1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L); // 线程休眠2秒，保证线程1先执行完，以便能模拟出来问题
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet("2020", "1000"));
            System.out.println(atomicReference.get());
        }, "线程2").start();

        TimeUnit.SECONDS.sleep(3L);

        System.out.println("====== ABA 问题解决 ======");

        new Thread(() -> {
            int stamp = stampedReference.getStamp(); // 获取版本号
            System.out.println("线程3 第一次操作时的版本号：" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("将原始值改为 2021");
            // 每操作一次，版本号+1
            stampedReference.compareAndSet("2020", "2021", stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(stampedReference.getReference());

            System.out.println("哎嘿，我又改回来了-_-");
            System.out.println("线程3 第二次操作时的版本号：" + stampedReference.getStamp());
            stampedReference.compareAndSet("2021", "2020", stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(stampedReference.getReference());
        }, "线程3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp(); // 获取版本号
            System.out.println("线程4 第一次操作时的版本号：" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(stampedReference.compareAndSet("2020", "2021", stamp, stamp + 1));
        }, "线程4").start();


        /**
         * int再包装为Integer时，如果int值在-128到127之间，会直接从缓存中获取；否则会直接new一个新的Integer对象。（缓存最大值一般就是127，有可能会通过虚拟机配置改为其他值）
         * 所以如果我们为两个Integer对象赋的值不在-128和127之间，则就会是两个不同的对象，用==比较，自然是返回false，
         * 所以上述的AtomicReference类中的泛型，如果示例中的值超过127，则就是两个对象，比较时肯定不是期望值，所以用String来作为泛型
         */
        TimeUnit.SECONDS.sleep(10L);
        System.out.println("讲述下为什么不用Integer作为泛型类");
        Integer a = 2020;
        Integer b = 2020;
        System.out.println(a == b);
    }
}
