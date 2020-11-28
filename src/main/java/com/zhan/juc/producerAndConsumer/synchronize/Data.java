package com.zhan.juc.producerAndConsumer.synchronize;

/**
 * @Author Zhanzhan
 * @Date 2020/11/28 13:12
 * 资源类
 */
public class Data {
    private int number = 0;

    /**
     * 增量方法，number + 1
     */
    public synchronized void increment() throws InterruptedException {
        while (number != 0){
            // 等待
            this.wait();
        }
        // 开始具体的业务逻辑
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 加完后，通知其他线程 +1 操作完毕了
        this.notifyAll();
    }

    /**
     * 减量操作，number - 1
     */
    public synchronized void decrement() throws InterruptedException {
        while (number == 0){
            // 等待
            this.wait();
        }
        // 开始具体的业务逻辑
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        // 减完后，通知其他线程 -1 操作完毕了
        this.notifyAll();
    }
}
