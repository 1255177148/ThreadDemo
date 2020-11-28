package com.zhan.juc.lock;

/**
 * @Author Zhanzhan
 * @Date 2020/11/27 20:51
 */
public class SaleTicketDemo1 {

    public static void main(String[] args) {
        // 并发，多个线程操作同一个资源类
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.saleBySynchronized();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.saleBySynchronized();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.saleBySynchronized();
            }
        }, "C").start();
    }
}
