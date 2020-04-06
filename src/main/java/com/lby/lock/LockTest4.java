package com.lby.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author macro
 * @Created on 2020-04-02
 */
public class LockTest4 {
    public static void main(String[] args) {
        Date date = new Date();
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
                    date.customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date.customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class Data2 {
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    /**
     * 生产者
     */
    public void product() throws InterruptedException {
        lock.lock();
        try {
            //判断状态
            while (num != 0) {
                condition.await();//等待
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            //完成加以后通知其他线程，我+1完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    /**
     * 消费者
     */
    public void customer() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();//等待
            }
            num--;
            //完成加以后通知其他线程，我-1完毕了
            System.out.println(Thread.currentThread().getName() + "->" + num);
            //完成加以后通知其他线程，我+1完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
