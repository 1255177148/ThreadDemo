package com.zhan.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/12/4 21:20
 * 阻塞队列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        // 会抛异常的方法
        ThrowTest throwTest = new ThrowTest();
//        throwTest.insert();
//        throwTest.delete();
//        throwTest.get();

        // 不会抛异常的方法
        NoThrow noThrow = new NoThrow();
//        noThrow.insert();
//        noThrow.delete();
        noThrow.get();

        // 会阻塞的方法
        Wait wait = new Wait();
        wait.insert();
    }
}

/**
 * 测试那些会抛异常的方法
 */
class ThrowTest{

    /**
     * 测试 add() , 发现会抛 java.lang.IllegalStateException
     */
    public void insert(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("d"));
    }

    /**
     * 测试获取并移除元素 remove() ，发现会抛 java.util.NoSuchElementException
     */
    public void delete(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }

    /**
     * 获取但不移除 头元素，使用element()，失败会抛 java.util.NoSuchElementException
     */
    public void get(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.element());
    }
}

/**
 * 不会抛异常的方法
 */
class NoThrow{

    /**
     * 添加元素，使用 offer()，失败不会抛异常
     */
    public void insert(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
    }

    /**
     * 获取并移除头元素，使用 poll()，失败不会抛异常
     */
    public void delete(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 获取但不移除 头元素，使用peek()，失败不会抛异常
     */
    public void get(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.peek());
    }
}

/**
 * 会阻塞的方法
 */
class Wait{

    /**
     * 添加元素， 使用 put()，如果队列满了，就一直阻塞
     * @throws InterruptedException
     */
    public void insert() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");
    }

    /**
     * 移除 头元素， 如果队列为空， 就一直阻塞
     * @throws InterruptedException
     */
    public void delete() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.take());
    }
}

/**
 * 会超时的方法
 */
class TimeOut{

    /**
     * 添加元素， 使用 offer()，如果队列满了，就会阻塞 指定时间，超时就退出
     * @throws InterruptedException
     */
    public void insert() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 2, TimeUnit.SECONDS));
    }

    /**
     * 移除 头元素，使用 poll(), 如果队列为空， 就会阻塞指定时间， 超时则退出
     * @throws InterruptedException
     */
    public void delete() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }
}
