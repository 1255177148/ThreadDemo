package com.zhan.juc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class JucApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 获取cpu的核数
     */
    @Test
    void getCountByCpu() throws InterruptedException {
        System.out.println("cpu的核数为：" + Runtime.getRuntime().availableProcessors());
        TimeUnit.SECONDS.sleep(1);
    }
}
