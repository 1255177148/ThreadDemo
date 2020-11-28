package com.zhan.juc.lock;

/**
 * @Author Zhanzhan
 * @Date 2020/11/27 21:14
 */
public class SaleTicketDemo2 {
    public static void main(String[]args){
        // 并发，多个线程操作同一个资源类
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.saleByLock();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.saleByLock();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.saleByLock();
            }
        }, "C").start();
    }
}