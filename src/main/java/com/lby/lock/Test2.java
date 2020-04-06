package com.lby.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author macro
 * @Created on 2020-04-02
 */
public class Test2 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}


/**
 * lock三部曲
 * 1、 new ReentrantLock();
 * 2、lock.lock();//加锁
 * 3、lock.unlock(;//释放锁
 *
 */
class Ticket2 {
    private int num = 30;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {

            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + num-- + "票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
