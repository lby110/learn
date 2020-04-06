package com.lby.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition实现精准唤醒
 *
 * @author macro
 * @Created on 2020-04-02
 */
public class LockTest5 {
    public static void main(String[] args) {
        Data5 date = new Data5();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date.customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date.customer2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}

class Data5 {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void product() throws InterruptedException {
        lock.lock();
        try {
            //判断状态
            while (num != 0) {
                condition1.await();//等待
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            //完成加以后唤醒线程2
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void customer() throws InterruptedException {
        lock.lock();
        try {
            while (num != 1) {
                condition2.await();//等待
            }
            num++;
            //完成加以后通知其他线程，我-1完毕了
            System.out.println(Thread.currentThread().getName() + "->" + num);
            //完成加以后通知其他线程，我+1完毕了
            condition3.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void customer2() throws InterruptedException {
        lock.lock();
        try {
            while (num != 2) {
                condition3.await();//等待
            }
            num = 0;
            //完成加以后通知其他线程，我-1完毕了
            System.out.println(Thread.currentThread().getName() + "->" + num);
            //完成加以后通知其他线程，我+1完毕了
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

