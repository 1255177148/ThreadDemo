package com.zhan.juc.help;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zhanzhan
 * @Date 2020/12/2 21:43
 * 设置线程可运行数量，限流用！！！
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);// 初始化，先设置 可运行线程数为3

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();// 获取线程运行许可证，即从Semaphore手中拿到一个许可证，Semaphore许可证数量 -1
                    System.out.println("线程 " + Thread.currentThread().getName() + "抢到一个许可证");
                    // 这里处理线程自己的业务逻辑，我这里是模拟，所以用休眠两秒来模拟处理业务
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("线程 " + Thread.currentThread().getName() + "返还一个许可证");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();// 将返还的许可证收回给Semaphore，即手中的许可证数量 +1
                }
            }, String.valueOf(i + 1)).start();
        }
    }
}
