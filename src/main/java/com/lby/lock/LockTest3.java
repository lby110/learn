package com.lby.lock;

/**
 * @author macro
 * @Created on 2020-04-02
 */
public class LockTest3 {
    public static void main(String[] args) {
        Date date=new Date();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    date.customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();


    }
}

class Date {
    private int num = 0;

    /**
     * 生产者
     */
    public synchronized void product() throws InterruptedException {
        //判断状态

        while (num != 0) {
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"->"+num);
        //完成加以后通知其他线程，我+1完毕了
        this.notifyAll();
    }

    /**
     * 消费者
     */
    public synchronized void customer() throws InterruptedException {
        while (num == 0) {
            this.wait();
        }
        num--;
        //完成加以后通知其他线程，我-1完毕了
        System.out.println(Thread.currentThread().getName()+"->"+num);
        this.notifyAll();
    }
}
