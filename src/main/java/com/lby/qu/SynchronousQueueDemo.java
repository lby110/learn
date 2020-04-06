package com.lby.qu;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author macro
 * @Created on 2020-04-05
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueueDemo synchronousQueueDemo=new SynchronousQueueDemo();
        synchronousQueueDemo.test();
    }
    public void test(){
        SynchronousQueue synchronousQueue=new SynchronousQueue();
        //创建一个线程进行input
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"T1");
                synchronousQueue.put("A");
                System.out.println(Thread.currentThread().getName()+"T2");

                synchronousQueue.put("B");
                System.out.println(Thread.currentThread().getName()+"T3");

                synchronousQueue.put("C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

    }
}
