package com.lby.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author macro
 * @Created on 2020-04-04
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //线程数量，停车位！限流
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();//得到车位
                    System.out.println(Thread.currentThread().getName() + "得到了车位");
                    TimeUnit.SECONDS.sleep(2);//休息2s
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();//释放
                }
            },String.valueOf(i)).start();
        }
    }
}
