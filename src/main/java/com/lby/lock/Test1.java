package com.lby.thread;

/**
 * @author macro
 * @Created on 2020-04-02
 */
public class Test1 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {for(int i = 0; i < 20; i++) {ticket.sale();}},"A").start();
        new Thread(() -> {for(int i = 0; i < 20; i++) {ticket.sale();}},"B").start();
        new Thread(() -> {for(int i = 0; i < 20; i++) {ticket.sale();}}, "C").start();
    }
}

class Ticket {
    private int num = 30;

    public synchronized void sale() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第" + num-- + "票");
        }
    }
}
